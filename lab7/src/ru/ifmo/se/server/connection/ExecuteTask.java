package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.server.command.CommandManager;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Task of request execution.
 *
 * @since 3.0
 * @author safarislava
 */
public class ExecuteTask implements Callable<Response> {
    private final Future<Request> request;
    private final CommandManager commandManager;
    private final Logger logger = Logger.getLogger(ExecuteTask.class.getName());

    /**
     * Standard constructor.
     *
     * @param request Value of future request
     * @param commandManager Value of commandManager
     */
    public ExecuteTask(Future<Request> request, CommandManager commandManager) {
        this.request = request;
        this.commandManager = commandManager;
    }

    @Override
    public Response call() {
        try {
            return commandManager.execute(request.get());
        }
        catch (Exception e) {
            logger.warning("Error while executing command: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
