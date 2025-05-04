package ru.ifmo.se.client.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.assembler.*;
import ru.ifmo.se.general.command.assembler.type.ClientRequired;
import ru.ifmo.se.general.command.assembler.CommandAssembler;
import ru.ifmo.se.general.command.assembler.type.CommandManagerRequired;
import ru.ifmo.se.general.common.AbstractCommandManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that recognize command from string words.
 * Class contains hashmap of commandBuilder that can assemble command objects.
 *
 * @since 1.0
 * @author safarislava
 */
public class CommandManager implements AbstractCommandManager {
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
            put(Action.CHECK_CONNECTION.get(), new AssemblerFactory(CheckCommandAssembler.class));
            put(Action.CLEAR.get(), new AssemblerFactory(ClearCommandAssembler.class));
            put(Action.COUNT_LESS_THAN_TYPE.get(), new AssemblerFactory(CountLessTypeCommandAssembler.class));
            put(Action.EXECUTE_SCRIPT.get(), new AssemblerFactory(ExecuteScriptCommandAssembler.class));
            put(Action.EXIT.get(), new AssemblerFactory(ExitCommandAssembler.class));
            put(Action.FILTER_CONTAINS_NAME.get(), new AssemblerFactory(FilterContainsNameCommandAssembler.class));
            put(Action.FILTER_FULL_NAME.get(), new AssemblerFactory(FilterFullNameCommandAssembler.class));
            put(Action.HELP.get(), new AssemblerFactory(HelpCommandAssembler.class));
            put(Action.INFO.get(), new AssemblerFactory(InfoCommandAssembler.class));
            put(Action.INSERT.get(), new AssemblerFactory(InsertCommandAssembler.class));
            put(Action.LOGIN.get(), new AssemblerFactory(LoginCommandAssembler.class));
            put(Action.REGISTER.get(), new AssemblerFactory(RegisterCommandAssembler.class));
            put(Action.REMOVE_ID.get(), new AssemblerFactory(RemoveCommandAssembler.class));
            put(Action.REMOVE_GREATER.get(), new AssemblerFactory(RemoveGreaterCommandAssembler.class));
            put(Action.REMOVE_GREATER_ID.get(), new AssemblerFactory(RemoveGreaterIdCommandAssembler.class));
            put(Action.REMOVE_LOWER.get(), new AssemblerFactory(RemoveLowerCommandAssembler.class));
            put(Action.SHOW.get(), new AssemblerFactory(ShowCommandAssembler.class));
            put(Action.UPDATE.get(), new AssemblerFactory(UpdateCommandAssembler.class));
        }};
    }

    /**
     * By string name of command recognize one and assemble command.
     *
     * @param name Value of command name
     * @param args Value of arguments this command
     */
    public CommandAssembler getAssembler(String name, String[] args) {
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
