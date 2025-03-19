package ru.ifmo.se.client.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.assembler.*;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;

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
            put("info", new AssemblerBuilder(InfoCommandAssembler.class));
            put("show", new AssemblerBuilder(ShowCommandAssembler.class));
            put("insert", new AssemblerBuilder(InsertCommandAssembler.class));
            put("update", new AssemblerBuilder(UpdateCommandAssembler.class));
            put("remove_id", new AssemblerBuilder(RemoveCommandAssembler.class));
            put("exit", new AssemblerBuilder(ExitCommandAssembler.class, client));
            put("clear", new AssemblerBuilder(ClearCommandAssembler.class));
            put("execute_script", new AssemblerBuilder(ExecuteScriptCommandAssembler.class, client));
            put("remove_greater", new AssemblerBuilder(RemoveGreaterCommandAssembler.class));
            put("remove_lower", new AssemblerBuilder(RemoveLowerCommandAssembler.class));
            put("remove_greater_id", new AssemblerBuilder(RemoveGreaterIdCommandAssembler.class));
            put("count_less_than_type", new AssemblerBuilder(CountLessTypeCommandAssembler.class));
            put("filter_by_full_name", new AssemblerBuilder(FilterFullNameCommandAssembler.class));
            put("filter_contains_name", new AssemblerBuilder(FilterContainsNameCommandAssembler.class));
            put("login", new AssemblerBuilder(LoginCommandAssembler.class, client));
            put("register", new AssemblerBuilder(RegisterCommandAssembler.class));
        }};
        this.assemblerBuilders.put("help", new AssemblerBuilder(HelpCommandAssembler.class, this));
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
