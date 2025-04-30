package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Request;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Task of receiving request.
 *
 * @since 3.0
 * @author safarislava
 */
public class RequestTask implements Callable<Request> {
    private final ClientManager clientManager;

    /**
     * Standard constructor.
     *
     * @param clientManager Valur of clientManager
     */
    public RequestTask(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public Request call() throws IOException {
        return clientManager.receiveRequest();
    }
}
