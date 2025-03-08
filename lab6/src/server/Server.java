package server;

import general.collection.OrganizationData;
import general.commands.builders.interfaces.CommandBuilder;
import server.common.ClientManager;
import server.common.CommandManager;

import java.io.IOException;

/**
 * Major class. Provides life cycle of server side program.
 *
 * @since 2.0
 * @author safarislava
 */
public class Server {
    private boolean running = false;

    private ClientManager clientManager;
    private final CommandManager commandManager;

    /**
     * Standard constructor.
     *
     * @param port Value of port
     * @param data Value of data access object
     */
    public Server(int port, OrganizationData data) {
        commandManager = new CommandManager(data);
        try {
            clientManager = new ClientManager(port);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Start lifecycle. Connect, receive, execute, send response.
     */
    public void start() {
        running = true;
        while (running) {
            clientManager.waitConnecting();

            while (running) {
                try {
                    CommandBuilder commandBuilder = clientManager.receiveCommandBuilder();

                    String response = commandManager.execute(commandBuilder);

                    clientManager.sendResponse(response);
                }
                catch (IOException e) {
                    break;
                }
            }
        }
    }

    /**
     * Stop lifecycle.
     */
    public void stop() {
        running = false;
    }
}
