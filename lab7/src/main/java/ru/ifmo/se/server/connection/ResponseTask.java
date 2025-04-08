package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Response;

import java.util.concurrent.CancellationException;
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
    private final Future<Response> responseFuture;
    private final Logger logger = Logger.getLogger(ResponseTask.class.getName());

    /**
     * Standard constructor.
     *
     * @param responseFuture Value of future sending response.
     * @param clientManager Value of clientManager
     */
    public ResponseTask(Future<Response> responseFuture, ClientManager clientManager) {
        this.clientManager = clientManager;
        this.responseFuture = responseFuture;
    }

    @Override
    public void run() {
        try {
            Response response = responseFuture.get();
            logger.info(response.getContent().trim());
            clientManager.sendResponse(response);
        } catch (Exception e) {
            logger.warning("Exception occurred while sending response");
            throw new RuntimeException(e);
        }
    }
}
