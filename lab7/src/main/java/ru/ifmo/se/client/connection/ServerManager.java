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
import java.util.Iterator;

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
    private final Selector selector;

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

        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

                socketChannel.register(selector, SelectionKey.OP_READ);

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
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isReadable()) return readResponse();
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Helpful method for exactly channel read logic.
     *
     * @return Value of response
     */
    private Response readResponse() {
        try {
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

        } catch (Exception e) {
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
