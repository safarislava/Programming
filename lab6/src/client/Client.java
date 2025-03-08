package client;

import client.common.CommandController;
import client.common.Input;
import client.common.ServerManager;
import general.commands.Command;
import general.commands.builders.interfaces.CommandBuilder;
import general.commands.builders.interfaces.ServerNeededCommandBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
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
    private final CommandController commandController;
    private final ServerManager serverManager;
    private final Set<String> scriptCalls = new HashSet<>();

    /**
     * Standard constructor.
     *
     */
    public Client(String host, int port, Input input) {
        this.input = input;
        commandController = new CommandController(this);
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

                CommandBuilder commandBuilder = commandController.prebuild(commandName, args);

                String response;
                if (commandBuilder instanceof ServerNeededCommandBuilder) {
                    serverManager.sendCommandBuilder(commandBuilder);
                    response = serverManager.receiveResponse();
                }
                else {
                    Command command = commandBuilder.build();
                    response = command.execute();
                }
                showText(response);
            }
            catch (IOException e) {
                serverManager.closeSocket();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
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
}
