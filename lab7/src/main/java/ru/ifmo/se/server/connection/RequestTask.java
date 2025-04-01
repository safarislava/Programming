package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Request;

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
    private final Logger logger = Logger.getLogger(RequestTask.class.getName());

    /**
     * Standard constructor.
     *
     * @param clientManager Valur of clientManager
     */
    public RequestTask(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public Request call() {
        try {
            return clientManager.receiveRequest();
        }
        catch (Exception e) {
            logger.warning("Error receiving request: " + e.getMessage());
            return new Request();
        }
    }
}
