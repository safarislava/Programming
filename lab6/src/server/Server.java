package server;

import general.collection.OrganizationData;
import general.commands.builders.interfaces.CommandBuilder;
import server.common.ClientManager;
import server.common.CommandManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

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

    private final Logger logger = Logger.getLogger(Server.class.getName());

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
        logger.info("Server started");
    }

    /**
     * Start lifecycle. Connect, receive, execute, send response.
     */
    public void start() {
        running = true;
        while (running) {
            logger.info("Waiting for connection...");
            clientManager.waitConnecting();
            logger.info("New connection established");

            while (running) {
                try {
                    logger.info("Waiting for command...");
                    CommandBuilder commandBuilder = clientManager.receiveCommandBuilder();
                    logger.info("New command established");

                    String response = commandManager.execute(commandBuilder);

                    logger.info("Sending response: ...");
                    clientManager.sendResponse(response);
                    logger.info(String.format("Sent response : %s", response.trim()));
                }
                catch (IOException e) {
                    logger.info("Socket disconnected");
                    break;
                }

                tryStop();
            }
        }
    }

    /**
     * Stop lifecycle.
     */
    public void tryStop() {
        try{
            if (System.in.available() > 0) {
                Scanner sc = new Scanner(System.in);
                if (sc.next().equals("exit")) {
                    running = false;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
