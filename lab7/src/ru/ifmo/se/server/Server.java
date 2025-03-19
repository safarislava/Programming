package ru.ifmo.se.server;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.data.UserData;
import ru.ifmo.se.server.collection.AuthOrganizationManager;
import ru.ifmo.se.server.connection.ClientManager;
import ru.ifmo.se.server.command.CommandManager;

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
     * @param organizationData Value of organizationData access object.
     * @param userData Value of userData
     */
    public Server(int port, OrganizationData organizationData, UserData userData) {
        AuthOrganizationManager authOrganizationManager = new AuthOrganizationManager(organizationData, userData);
        commandManager = new CommandManager(authOrganizationManager, userData);

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
                    Request request = clientManager.receiveRequest();
                    logger.info("New command established");

                    Response response = commandManager.execute(request);

                    logger.info("Sending response: ...");
                    clientManager.sendResponse(response);
                    logger.info(String.format("Sent response : %s", response.getContent()));
                }
                catch (IOException e) {
                    logger.info("Socket disconnected");
                    break;
                }
                catch (Exception e) {
                    logger.warning(e.getMessage());
                }

                tryStop();
            }
        }
    }

    /**
     * Stop lifecycle.
     */
    public void tryStop() {
        try {
            if (System.in.available() > 0) {
                Scanner scanner = new Scanner(System.in);
                if (scanner.next().equals("exit")) {
                    running = false;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
