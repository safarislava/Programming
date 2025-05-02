package ru.ifmo.se.server.connection;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
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
    private boolean busy = false;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    private final Logger logger = Logger.getLogger(ClientManager.class.getName());

    /**
     * Standard constructor.
     *
     * @param socket Value of socket
     */
    public ClientManager(Socket socket) {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        }
        catch (IOException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for receiving CommandBuilder or something implemented.
     * Block program.
     *
     * @return CommandBuilder
     */
    public Request receiveRequest() throws IOException {
        try {
            ObjectInputStream deserializer = new ObjectInputStream(inputStream);
            return (Request) deserializer.readObject();
        }
        catch (ClassNotFoundException e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for sending response for client.
     * Block program.
     *
     * @param response String of response.
     */
    public void sendResponse(Response response) throws IOException {
        ByteBuffer sendingBytes = ByteBuffer.allocate(4 + response.getSize())
                .putInt(response.getSize()).put(response.getContent().getBytes());

        outputStream.write(sendingBytes.array());
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
