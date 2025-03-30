package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Task for sending response to client.
 *
 * @since 3.0
 * @author safarislava
 */
public class ResponseTask implements Runnable {
    private final ClientManager clientManager;
    private final Future<Response> response;
    private final Logger logger = Logger.getLogger(ResponseTask.class.getName());

    /**
     * Standard constructor.
     *
     * @param response Value of future sending response.
     * @param clientManager Value of clientManager
     */
    public ResponseTask(Future<Response> response, ClientManager clientManager) {
        this.clientManager = clientManager;
        this.response = response;
    }

    @Override
    public void run() {
        try {
           clientManager.sendResponse(response.get());
        } catch (ExecutionException | InterruptedException e) {
            clientManager.sendResponse(new Response("Failed\n"));

            logger.warning("Future exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
