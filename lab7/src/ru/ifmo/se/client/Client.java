package ru.ifmo.se.client;

import ru.ifmo.se.client.commands.CommandManager;
import ru.ifmo.se.general.contracts.Request;
import ru.ifmo.se.general.contracts.Response;
import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.client.connection.ServerManager;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.ServerNeededCommandBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Major class. Provides life cycle of client side program.
 * Wait for command and transit to CommandManager for recognizing.
 *
 * @since 1.0
 * @author safarislava
 */
public class Client {
    private boolean running = false;
    private Input input;
    private final CommandManager commandManager;
    private final ServerManager serverManager;
    private final Set<String> scriptCalls = new HashSet<>();

    private String username;
    private String password;

    /**
     * Standard constructor.
     *
     */
    public Client(String host, int port, Input input) {
        this.input = input;
        commandManager = new CommandManager(this);
        serverManager = new ServerManager(host, port);
    }

    /**
     * Start listening input for command
     */
    public void start() {
        running = true;

        while (running && input.hasNext()) {
            while (!serverManager.isConnected()) {
                serverManager.tryConnectServer();
            }

            try {
                String[] commandArray = input.getCommandArray();
                String commandName = commandArray[0];
                String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

                CommandBuilder commandBuilder = commandManager.prebuild(commandName, args);

                Request request = new Request(username, password, commandBuilder);
                Response response;
                if (commandBuilder instanceof ServerNeededCommandBuilder) {
                    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                        showText("You didn't login\n");
                        continue;
                    }

                    serverManager.sendRequest(request);
                    response = serverManager.receiveResponse();
                }
                else {
                    Command command = commandBuilder.build();
                    response = new Response(command.execute());
                }
                showText(response.content);
            }
            catch (IOException e) {
                serverManager.closeSocket();
            }
            catch (NoSuchElementException e){
                stop();
            }
            catch (Exception e) {
                showText(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Stop listening
     */
    public void stop() {
        running = false;
        serverManager.closeSocket();
    }

    /**
     * Getter of input field.
     *
     * @return Value of input
     */
    public Input getInput() {
        return input;
    }

    /**
     * Setter of input field.
     *
     * @param input Value of input
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * Method provide show text for user.
     *
     * @param text Value of text
     */
    public void showText(String text) {
        System.out.print(text);
    }

    /**
     * Check that this call didn't start
     *
     * @param call Name of call
     * @return True if contain
     */
    public boolean containCall(String call) {
        return scriptCalls.contains(call);
    }

    /**
     * Add call. Checking valid.
     *
     * @param call Name of call
     */
    public void addCall(String call) {
        if (containCall(call)) throw new RuntimeException("Recursive call");
        scriptCalls.add(call);
    }

    /**
     * Remove call.
     *
     * @param call Name of call
     */
    public void removeCall(String call) {
        scriptCalls.remove(call);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
