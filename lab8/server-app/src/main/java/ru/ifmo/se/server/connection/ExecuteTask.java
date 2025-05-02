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
    private final Future<Request> requestFuture;
    private final CommandManager commandManager;

    /**
     * Standard constructor.
     *
     * @param requestFuture Value of future request
     * @param commandManager Value of commandManager
     */
    public ExecuteTask(Future<Request> requestFuture, CommandManager commandManager) {
        this.requestFuture = requestFuture;
        this.commandManager = commandManager;
    }

    @Override
    public Response call() throws Exception {
        return commandManager.execute(requestFuture.get());
    }
}
