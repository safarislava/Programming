package ru.ifmo.se.server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Task of connection with client. Blocking mode.
 *
 * @since 3.0
 * @author safarislava
 */
public class ConnectionTask {
    private final ServerSocket serverSocket;
    private final Logger logger = Logger.getLogger(ConnectionTask.class.getSimpleName());

    /**
     * Standard constructor.
     *
     * @param port Value of port
     */
    public ConnectionTask(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.severe("Could not listen on port: " + port);
            throw new RuntimeException(e);
        }
    }

    /**
     * Method provides building new client socket.
     *
     * @return Value of socket
     */
    public Socket connect () {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            logger.warning("Could not accept connection");
            throw new RuntimeException(e);
        }
    }

    /**
     * Method provides closing server socket.
     */
    public void close() {
        try {
            serverSocket.close();
        }
        catch (IOException e) {
            logger.warning("Could not close server socket");
        }
    }
}
