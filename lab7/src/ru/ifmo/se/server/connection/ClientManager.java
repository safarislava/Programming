package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contracts.Request;
import ru.ifmo.se.general.contracts.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
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
    private ServerSocket serverSocket;
    private Socket socket;

    private final Logger logger = Logger.getLogger(ClientManager.class.getName());

    /**
     * Standard constructor.
     *
     * @param port Value of port
     */
    public ClientManager(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.severe("Could not listen on port: " + port);
        }
    }

    /**
     * Method for accepting connecting. Block program.
     */
    public void waitConnecting() {
        try {
            socket = serverSocket.accept();
        }
        catch (IOException e) {
            logger.warning("Accept failed");
        }
    }

    /**
     * Method for receiving CommandBuilder or something implemented.
     * Block program.
     *
     * @return CommandBuilder
     * @throws IOException Exception if something wrong with socket
     */
    public Request receiveRequest() throws IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream deserializer = new ObjectInputStream(inputStream);
            return (Request) deserializer.readObject();
        }
        catch (ClassNotFoundException e) {
            logger.warning("Class not found");
        }
        return null;
    }

    /**
     * Method for sending response for client.
     * Block program.
     *
     * @param response String of response.
     * @throws IOException Exception if something wrong with socket
     */
    public void sendResponse(Response response) throws IOException {
        ByteBuffer sendingBytes = ByteBuffer.allocate(4+response.getSize())
                .putInt(response.getSize()).put(response.getContent().getBytes());

        socket.getOutputStream().write(sendingBytes.array());
    }
}
