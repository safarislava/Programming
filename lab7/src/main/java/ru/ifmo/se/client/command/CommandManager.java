package ru.ifmo.se.client.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.assembler.*;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;
import ru.ifmo.se.general.command.assembler.CommandAssembler;
import ru.ifmo.se.general.command.assembler.type.CommandManagerRequired;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that recognize command from string words.
 * Class contains hashmap of commandBuilder that can assemble command objects.
 *
 * @since 1.0
 * @author safarislava
 */
public class CommandManager {
    private final Map<String, AssemblerFactory> assemblerFactories;
    private final Client client;

    /**
     * Standard constructor
     *
     * @param client Value of class providing lifecycle of program
     */
    public CommandManager(Client client) {
        this.client = client;
        this.assemblerFactories = new HashMap<>(){{
            put("clear", new AssemblerFactory(ClearCommandAssembler.class));
            put("count_less_than_type", new AssemblerFactory(CountLessTypeCommandAssembler.class));
            put("execute_script", new AssemblerFactory(ExecuteScriptCommandAssembler.class));
            put("exit", new AssemblerFactory(ExitCommandAssembler.class));
            put("filter_contains_name", new AssemblerFactory(FilterContainsNameCommandAssembler.class));
            put("filter_by_full_name", new AssemblerFactory(FilterFullNameCommandAssembler.class));
            put("help", new AssemblerFactory(HelpCommandAssembler.class));
            put("info", new AssemblerFactory(InfoCommandAssembler.class));
            put("insert", new AssemblerFactory(InsertCommandAssembler.class));
            put("login", new AssemblerFactory(LoginCommandAssembler.class));
            put("register", new AssemblerFactory(RegisterCommandAssembler.class));
            put("remove_id", new AssemblerFactory(RemoveCommandAssembler.class));
            put("remove_greater", new AssemblerFactory(RemoveGreaterCommandAssembler.class));
            put("remove_greater_id", new AssemblerFactory(RemoveGreaterIdCommandAssembler.class));
            put("remove_lower", new AssemblerFactory(RemoveLowerCommandAssembler.class));
            put("show", new AssemblerFactory(ShowCommandAssembler.class));
            put("update", new AssemblerFactory(UpdateCommandAssembler.class));
        }};
    }

    /**
     * By string name of command recognize one and assemble command.
     *
     * @param name Value of command name
     * @param args Value of arguments this command
     */
    public CommandAssembler preassemble(String name, String[] args) {
        AssemblerFactory factory = assemblerFactories.get(name);
        if (factory == null) {
            throw new RuntimeException("No command found for name : " + name);
        }

        CommandAssembler assembler = factory.build();

        if (assembler instanceof ClientRequired) {
            ((ClientRequired) assembler).setClient(client);
        }
        if (assembler instanceof CommandManagerRequired) {
            ((CommandManagerRequired) assembler).setCommandManager(this);
        }
        assembler.setArguments(args, client.getInput());

        return assembler;
    }

    /**
     * Getter of main routing hashmap
     *
     * @return Value of hashmap
     */
    public Map<String, CommandAssembler> getCommandAssemblers() {
        Map<String, CommandAssembler> commandAssemblers = new HashMap<>();

        assemblerFactories.forEach((name, assembler) ->
                commandAssemblers.put(name, assembler.build()));

        return commandAssemblers;
    }
}
