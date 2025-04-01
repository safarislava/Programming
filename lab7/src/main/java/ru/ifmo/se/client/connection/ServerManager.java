package ru.ifmo.se.client.connection;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;

/**
 * Class for managing server connection.
 * Provides connecting, receiving and sending information.
 *
 * @since 2.0
 * @author safarislava
 */
public class ServerManager {
    private final String host;
    private final int port;

    private static final int BUFFER_SIZE = 1024;
    private static final int WAIT_TIME = 1000;

    private SocketChannel socketChannel;
    private final ByteBuffer buffer;

    private boolean connected = false;

    /**
     * Standard constructor.
     *
     * @param host Name of hosting
     * @param port Value of port number
     */
    public ServerManager(String host, int port) {
        this.host = host;
        this.port = port;
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    public boolean isConnected() {
        return socketChannel != null && connected;
    }

    /**
     * Method that trying to connect server while it has done.
     */
    public void guarantyConnection() {
        while (!isConnected()) {
            try {
                Thread.sleep(WAIT_TIME);
                socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
                socketChannel.configureBlocking(false);

                connected = true;
                break;
            } catch (Exception ignored) {
                System.out.println("Exception: Connection failed");
            }
        }
    }


    /**
     * Method to send object implemented of CommandBuilder.
     * Can throw an exception.
     *
     * @param request Value of request
     */
    public void sendRequest(Request request) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream serializer = new ObjectOutputStream(byteStream);

            serializer.writeObject(request);

            socketChannel.write(ByteBuffer.wrap(byteStream.toByteArray()));
        } catch (IOException e) {
            connected = false;
            throw new RuntimeException(e);
        }
    }

    /**
     *  Method to receive strings of response.
     *  Can throw an exception.
     *
     * @return String of response.
     */
    public Response receiveResponse() {
        try {
            StringBuilder content = new StringBuilder();
            int countBytes = 0;
            boolean done;
            do {
                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();
                try {
                    countBytes = buffer.getInt();
                    done = countBytes > 0;
                } catch (RuntimeException e) {
                    done = false;
                }
            } while (!done);

            countBytes -= buffer.remaining();
            content.append(StandardCharsets.UTF_8.decode(buffer));

            while (countBytes > 0) {
                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();
                countBytes -= buffer.remaining();
                content.append(StandardCharsets.UTF_8.decode(buffer));
            }
            return new Response(content.toString());

        } catch (IOException e) {
            connected = false;
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to try close socket connection.
     */
    public void closeSocket() {
        try {
            connected = false;
            socketChannel.close();
        } catch (IOException e) {
            System.out.printf("Exception: Failed to close socket: %s%n", e.getMessage());
        }
    }
}
