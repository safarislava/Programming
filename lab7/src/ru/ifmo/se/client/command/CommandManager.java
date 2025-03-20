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
 * Class contains hashmap of commandBuilder that can assembl command objects.
 *
 * @since 1.0
 * @author safarislava
 */
public class CommandManager {
    private final Map<String, AssemblerBuilder> assemblerBuilders;
    private final Client client;

    /**
     * Standard constructor
     *
     * @param client Value of class providing lifecycle of program
     */
    public CommandManager(Client client) {
        this.client = client;
        this.assemblerBuilders = new HashMap<>(){{
            put("clear", new AssemblerBuilder(ClearCommandAssembler.class));
            put("count_less_than_type", new AssemblerBuilder(CountLessTypeCommandAssembler.class));
            put("execute_script", new AssemblerBuilder(ExecuteScriptCommandAssembler.class));
            put("exit", new AssemblerBuilder(ExitCommandAssembler.class));
            put("filter_contains_name", new AssemblerBuilder(FilterContainsNameCommandAssembler.class));
            put("filter_by_full_name", new AssemblerBuilder(FilterFullNameCommandAssembler.class));
            put("help", new AssemblerBuilder(HelpCommandAssembler.class));
            put("info", new AssemblerBuilder(InfoCommandAssembler.class));
            put("insert", new AssemblerBuilder(InsertCommandAssembler.class));
            put("login", new AssemblerBuilder(LoginCommandAssembler.class));
            put("register", new AssemblerBuilder(RegisterCommandAssembler.class));
            put("remove_id", new AssemblerBuilder(RemoveCommandAssembler.class));
            put("remove_greater", new AssemblerBuilder(RemoveGreaterCommandAssembler.class));
            put("remove_greater_id", new AssemblerBuilder(RemoveGreaterIdCommandAssembler.class));
            put("remove_lower", new AssemblerBuilder(RemoveLowerCommandAssembler.class));
            put("show", new AssemblerBuilder(ShowCommandAssembler.class));
            put("update", new AssemblerBuilder(UpdateCommandAssembler.class));
        }};
    }

    /**
     * By string name of command recognize one and assembl command.
     *
     * @param name Value of command name
     * @param args Value of arguments this command
     */
    public CommandAssembler preassemble(String name, String[] args) {
        AssemblerBuilder builder = assemblerBuilders.get(name);
        if (builder == null) {
            throw new RuntimeException("No command found for name : " + name);
        }

        CommandAssembler assembler = builder.build();

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

        assemblerBuilders.forEach((name, assembler) ->
                commandAssemblers.put(name, assembler.build()));

        return commandAssemblers;
    }
}
