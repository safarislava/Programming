package server.common;

import general.commands.builders.interfaces.CommandBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


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

    /**
     * Standard constructor.
     *
     * @param port Value of port
     */
    public ClientManager(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
        }
    }

    /**
     * Method for accepting connecting. Block program.
     */
    public void waitConnecting() {
        try {
            socket = serverSocket.accept();
            socket.getSoTimeout();
        }
        catch (IOException e) {
            System.err.println("Accept failed");
        }
    }

    /**
     * Method for receiving CommandBuilder or something implemented.
     * Block program.
     *
     * @return CommandBuilder
     * @throws IOException Exception if something wrong with socket
     */
    public CommandBuilder receiveCommandBuilder() throws IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream deserializer = new ObjectInputStream(inputStream);
            return (CommandBuilder) deserializer.readObject();
        }
        catch (ClassNotFoundException e) {
            System.err.println("Class not found");
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
    public void sendResponse(String response) throws IOException {
        socket.getOutputStream().write(response.getBytes());
    }
}
