package ru.ifmo.se.client;

import ru.ifmo.se.client.command.CommandManager;
import ru.ifmo.se.client.command.Action;
import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.exeption.ClosedWindow;
import ru.ifmo.se.general.parser.Parser;
import ru.ifmo.se.client.connection.ServerManager;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.assembler.CommandAssembler;
import ru.ifmo.se.general.command.assembler.type.ServerRequired;

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
        serverManager.guarantyConnection();
    }

    /**
     * Stop listening
     */
    public void stop() {
        serverManager.closeSocket();
    }

    public Response execute(Action name, String[] args) {
        return execute(name.get(), args);
    }

    public Response execute(String name, String[] args) {
        try {
            CommandAssembler commandAssembler = commandManager.getAssembler(name, args);

            Request request = new Request(username, password, commandAssembler);

            Response response;
            if (commandAssembler instanceof ServerRequired) {
                serverManager.sendRequest(request);
                response = serverManager.receiveResponse();
            }
            else {
                Command command = commandAssembler.assemble();
                response = new Response(command.execute());
            }
            return response;
        }
        catch (NullPointerException | ClosedWindow e) {
            return new Response(CodePhrase.SKIP);
        }
        catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return new Response(CodePhrase.FAILED);
        }
    }

    public boolean isCorrectLogin() {
        boolean existed = username != null && !username.isEmpty() && password != null && !password.isEmpty();
        if (!existed) return false;

        Response response = execute(Action.CHECK_CONNECTION, new String[0]);
        return !response.getContent().equals(CodePhrase.INCORRECT_LOGIN);
    }

    public boolean register(String username, String password) {
        boolean existed = username != null && !username.isEmpty() && password != null && !password.isEmpty();
        if (!existed) return false;

        Response response = execute(Action.REGISTER, new String[]{username, password});
        return response.getContent().equals(CodePhrase.SUCCESSFUL);
    }

    public String getOrganizations() {
        Response response = execute(Action.SHOW, new String[0]);
        return response.getContent();
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

    /**
     * Setter of username.
     *
     * @param username Value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Setter of password.
     *
     * @param password Value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
