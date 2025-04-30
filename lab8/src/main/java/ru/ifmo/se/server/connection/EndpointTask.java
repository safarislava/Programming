package ru.ifmo.se.server.connection;

import ru.ifmo.se.server.Server;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class EndpointTask implements Runnable {
    private final Server server;
    private final Thread responseThread;
    private final ClientManager client;
    private static final Logger logger = Logger.getLogger(EndpointTask.class.getName());

    public EndpointTask(Server server, ClientManager client, Thread responseThread) {
        this.server = server;
        this.client = client;
        this.responseThread = responseThread;
    }

    @Override
    public void run() {
        AtomicInteger countExceptions = new AtomicInteger();

        responseThread.setUncaughtExceptionHandler(
                (t, e) -> countExceptions.getAndIncrement());

        responseThread.start();

        try {
            responseThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (countExceptions.get() > 0) {
            server.removeClient(client);
            logger.info("Client disconnected");
        }

        client.setBusy(false);
    }
}
