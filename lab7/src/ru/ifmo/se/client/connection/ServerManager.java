package ru.ifmo.se.client.connection;

import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
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

    /**
     * Method that trying to connect server while it has done.
     */
    public void tryConnectServer() {
        try {
            Thread.sleep(WAIT_TIME);
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        }
        catch (IOException e) {
            System.out.printf("Failed to connect to %s:%s%n", host, port);
        }
        catch (InterruptedException e) {
            System.out.printf(e.getMessage());
        }
    }

    /**
     * Method to check status of connection.
     * Maybe work incorrectly, because socket doesn't return that information.
     *
     * @return True if connected
     */
    public boolean isConnected() {
        return socketChannel != null && socketChannel.isConnected();
    }

    /**
     * Method to send object implemented of CommandBuilder.
     * Can throw an exception.
     *
     * @param request Value of request
     * @throws IOException Exception if socket problems.
     */
    public void sendRequest(Request request) throws IOException {
        OutputStream out = socketChannel.socket().getOutputStream();
        ObjectOutputStream serializer = new ObjectOutputStream(out);

        serializer.writeObject(request);
    }

    /**
     *  Method to receive strings of response.
     *  Can throw an exception.
     *
     * @return String of response.
     * @throws IOException Exception if socket problems.
     */
    public Response receiveResponse() throws IOException {
        StringBuilder content = new StringBuilder();

        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();

        int countBytes = buffer.getInt();
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
    }

    /**
     * Method to try close socket connection.
     */
    public void closeSocket() {
        try {
            socketChannel.close();
        }
        catch (IOException e) {
            System.out.printf("Failed to close socket: %s%n", e.getMessage());
        }
    }
}
