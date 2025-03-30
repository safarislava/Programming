package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Logger;


/**
 * Class providing connection with clients.
 * Contain methods for connection, sending and receiving info.
 *
 * @since 2.0
 * @author safarislava
 */
public class ClientManager {
    private final Socket socket;

    private final Logger logger = Logger.getLogger(ClientManager.class.getName());

    /**
     * Standard constructor.
     *
     * @param socket Value of socket
     */
    public ClientManager(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method for receiving CommandBuilder or something implemented.
     * Block program.
     *
     * @return CommandBuilder
     */
    public Request receiveRequest() {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream deserializer = new ObjectInputStream(inputStream);
            return (Request) deserializer.readObject();
        }
        catch (ClassNotFoundException e) {
            logger.warning("ClassNotFoundException: " + e.getMessage());
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            logger.warning("IOException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for sending response for client.
     * Block program.
     *
     * @param response String of response.
     */
    public void sendResponse(Response response) {
        try {
            ByteBuffer sendingBytes = ByteBuffer.allocate(4+response.getSize())
                    .putInt(response.getSize()).put(response.getContent().getBytes());

            socket.getOutputStream().write(sendingBytes.array());
        }
        catch (IOException e) {
            logger.warning("IOException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
