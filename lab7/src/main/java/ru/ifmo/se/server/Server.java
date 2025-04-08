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
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Major class. Provides life cycle of server side program.
 *
 * @since 2.0
 * @author safarislava
 */
public class Server {
    private static final int MAX_CONNECTIONS = 2;

    private boolean running = false;

    private final ConnectionTask connectionTask;
    private final CommandManager commandManager;

    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private final Set<Socket> sockets = new HashSet<>();

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

        while (running) {
            if (sockets.size() >= MAX_CONNECTIONS) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                continue;
            }
            logger.info("Waiting for connection");
            Socket socket = connectionTask.connect();
            sockets.add(socket);
            ClientManager clientManager = new ClientManager(socket);
            logger.info(String.format("Client connected, connected %d sockets", sockets.size()));

            Thread connectionThread = new Thread(() -> {
                while (running) {
                    try {
                        AtomicInteger countExceptions = new AtomicInteger();

                        Future<Request> request = cachedThreadPool.submit(new RequestTask(clientManager));
                        Future<Response> response = forkJoinPool.submit(new ExecuteTask(request, commandManager));
                        Thread responseThread = new Thread(new ResponseTask(response, clientManager));
                        responseThread.setUncaughtExceptionHandler(
                                (t, e) -> countExceptions.getAndIncrement());

                        responseThread.start();
                        responseThread.join();

                        if (countExceptions.get() > 0) {
                            sockets.remove(clientManager.getSocket());
                            logger.info(String.format("Client disconnected, connected %d sockets", sockets.size()));
                            break;
                        }
                    } catch (Exception e) {
                        logger.warning(e.toString());
                        break;
                    }
                }
            });

            logger.info("Starting client thread");
            connectionThread.start();
        }
    }

    /**
     * Stop lifecycle.
     */
    public void startStoppingThread() {
        Thread stoppingThread = new Thread(() -> {
            while (running) {
                try {
                    if (System.in.available() > 0) {
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.next().equals("exit")) {
                            logger.info("Server stopped");
                            System.exit(0);
                        }
                    }
                } catch (IOException e) {
                    logger.warning(e.toString());
                }
            }
        });
        stoppingThread.start();
    }
}
