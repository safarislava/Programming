package ru.ifmo.se.server;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.data.UserData;
import ru.ifmo.se.server.collection.AuthOrganizationManager;
import ru.ifmo.se.server.connection.*;
import ru.ifmo.se.server.command.CommandManager;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Major class. Provides life cycle of ru.ifmo.se.server side program.
 *
 * @since 2.0
 * @author safarislava
 */
public class Server {
    private boolean running = false;

    private final ConnectionTask connectionTask;
    private final CommandManager commandManager;

    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private final ConcurrentHashMap.KeySetView<ClientManager, Boolean> clients = ConcurrentHashMap.newKeySet();

    private static final int WAIT_TIME = 1000;

    private final Logger logger = Logger.getLogger(Server.class.getName());

    /**
     * Standard constructor.
     *
     * @param port Value of port
     * @param organizationData Value of organizationData access object.
     * @param userData Value of userData
     */
    public Server(int port, OrganizationData organizationData, UserData userData) {
        AuthOrganizationManager authOrganizationManager = new AuthOrganizationManager(organizationData, userData);
        commandManager = new CommandManager(authOrganizationManager, userData);

        connectionTask = new ConnectionTask(port);

        logger.info("Server started");
    }

    /**
     * Start lifecycle. Connect, receive, execute, send response.
     */
    public void start() {
        running = true;
        startStoppingThread();

        Thread updateThread = new Thread(() -> {
            while (running) {
                for (ClientManager client : clients) {
                    if (client.isBusy()) continue;
                    client.setBusy(true);

                    Future<Request> request = cachedThreadPool.submit(new RequestTask(client));
                    Future<Response> response = forkJoinPool.submit(new ExecuteTask(request, commandManager));
                    Thread responseThread = new Thread(new ResponseTask(response, client));
                    Thread endpointThread = new Thread(new EndpointTask(this, client, responseThread));
                    endpointThread.start();
                }

                try {
                    Thread.sleep(WAIT_TIME);
                }
                catch (Exception ignored) {}
            }
        });
        updateThread.start();

        while (running) {
            logger.info("Waiting for connection");
            Socket socket = connectionTask.connect();
            ClientManager client = new ClientManager(socket);
            clients.add(client);
            logger.info(String.format("Client connected, connected %d sockets", clients.size()));
        }
    }

    /**
     * Stop lifecycle.
     */
    public void startStoppingThread() {
        Thread stoppingThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(WAIT_TIME);
                    if (System.in.available() > 0) {
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.next().equals("exit")) {
                            logger.info("Server stopped");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    logger.warning(e.toString());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stoppingThread.start();
    }

    public void removeClient(ClientManager client) {
        clients.remove(client);
    }
}
