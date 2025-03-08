package client.common;

import client.Client;
import general.commands.builders.*;
import general.commands.builders.interfaces.CommandBuilder;

import java.util.HashMap;

/**
 * Class that recognize command from string words.
 * Class contains hashmap of commandBuilder that can build command objects.
 *
 * @since 1.0
 * @author safarislava
 */
public class CommandController {
    private final HashMap<String, CommandBuilder> commandBuilders;
    private final Client client;

    /**
     * Standard constructor
     *
     * @param client Value of class providing lifecycle of program
     */
    public CommandController(Client client) {
        this.client = client;
        this.commandBuilders = new HashMap<>(){{
            put("info", new InfoCommandBuilder());
            put("show", new ShowCommandBuilder());
            put("insert", new InsertCommandBuilder());
            put("update", new UpdateCommandBuilder());
            put("remove_id", new RemoveCommandBuilder());
            put("exit", new ExitCommandBuilder(client));
            put("clear", new ClearCommandBuilder());
            put("execute_script", new ExecuteScriptCommandBuilder(client));
            put("remove_greater", new RemoveGreaterCommandBuilder());
            put("remove_lower", new RemoveLowerCommandBuilder());
            put("remove_greater_id", new RemoveGreaterIdCommandBuilder());
            put("count_less_than_type", new CountLessTypeCommandBuilder());
            put("filter_by_full_name", new FilterFullNameCommandBuilder());
            put("filter_contains_name", new FilterContainsNameCommandBuilder());
        }};
        this.commandBuilders.put("help", new HelpCommandBuilder(this));
    }

    /**
     * By string name of command recognize one and build command.
     *
     * @param name Value of command name
     * @param args Value of arguments this command
     */
    public CommandBuilder prebuild(String name, String[] args) {
        CommandBuilder builder = commandBuilders.get(name);

        if (builder == null) {
            throw new RuntimeException("No command found for name : " + name);
        }

        builder.setArguments(args, client.getInput());

        return builder;
    }

    /**
     * Getter of main routing hashmap
     *
     * @return Value of hashmap
     */
    public HashMap<String, CommandBuilder> getCommandBuilders() {
        return commandBuilders;
    }
}
