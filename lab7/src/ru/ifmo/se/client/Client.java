package ru.ifmo.se.client;

import ru.ifmo.se.client.command.CommandManager;
import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.Parser;
import ru.ifmo.se.client.connection.ServerManager;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;
import ru.ifmo.se.general.command.assembler.type.ServerNeededCommandAssembler;

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
    private Parser parser;
    private final CommandManager commandManager;
    private final ServerManager serverManager;
    private final Set<String> scriptCalls = new HashSet<>();

    private String username;
    private String password;

    /**
     * Standard constructor.
     *
     */
    public Client(String host, int port, Parser parser) {
        this.parser = parser;
        commandManager = new CommandManager(this);
        serverManager = new ServerManager(host, port);
    }

    /**
     * Start listening input for command
     */
    public void start() {
        running = true;

        while (running && parser.hasNext()) {
            while (!serverManager.isConnected()) {
                serverManager.tryConnectServer();
            }

            try {
                String[] commandArray = parser.getCommandArray();
                String commandName = commandArray[0];
                String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

                CommandAssembler commandAssembler = commandManager.preassemble(commandName, args);

                Request request = new Request(username, password, commandAssembler);

                Response response;
                if (commandAssembler instanceof ServerNeededCommandAssembler) {
                    if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                        showText("You didn't login\n");
                        continue;
                    }
                    serverManager.sendRequest(request);
                    response = serverManager.receiveResponse();
                }
                else {
                    Command command = commandAssembler.assemble();
                    response = new Response(command.execute());
                }
                showText(response.getContent());
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
    public Parser getInput() {
        return parser;
    }

    /**
     * Setter of input field.
     *
     * @param parser Value of input
     */
    public void setInput(Parser parser) {
        this.parser = parser;
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
