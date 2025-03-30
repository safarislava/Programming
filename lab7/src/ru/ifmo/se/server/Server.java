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
import java.util.logging.Logger;

/**
 * Major class. Provides life cycle of server side program.
 *
 * @since 2.0
 * @author safarislava
 */
public class Server {
    private static final int MAX_CONNECTIONS = 5;

    private boolean running = false;

    private final ConnectionTask connectionTask;
    private final CommandManager commandManager;

    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private final Set<Thread> connectionThreads = new HashSet<>();

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

        Thread stoppingThread = new Thread(this::tryStop);
        stoppingThread.start();

        while (running && connectionThreads.size() < MAX_CONNECTIONS) {
            logger.info("Waiting for connection");
            Socket socket = connectionTask.connect();
            ClientManager clientManager = new ClientManager(socket);
            logger.info("Client connected");

            Thread connectionThread = new Thread(() -> {
                while (running) {
                    try {
                        Future<Request> request = cachedThreadPool.submit(new RequestTask(clientManager));
                        Future<Response> response = forkJoinPool.submit(new ExecuteTask(request, commandManager));
                        Thread responseThread = new Thread(new ResponseTask(response, clientManager));
                        responseThread.start();
                        responseThread.join();
                    } catch (Exception e) {
                        logger.warning(e.getMessage());
                        connectionThreads.remove(Thread.currentThread());
                        Thread.currentThread().interrupt();
                    }
                }
            });
            logger.info("Starting client thread");
            connectionThread.start();
            connectionThreads.add(connectionThread);
        }
    }

    /**
     * Stop lifecycle.
     */
    public void tryStop() {
        while (running) {
            try {
                Thread.sleep(1000);
                if (System.in.available() > 0) {

                    Scanner scanner = new Scanner(System.in);
                    if (scanner.next().equals("exit")) {
                        logger.info("Server stopped");
                        running = false;

                        for (Thread thread : connectionThreads) {
                            thread.interrupt();
                        }

                        cachedThreadPool.shutdownNow();
                        forkJoinPool.shutdownNow();
                    }
                }
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.currentThread().interrupt();
    }
}
