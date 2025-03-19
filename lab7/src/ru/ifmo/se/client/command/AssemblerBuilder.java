package ru.ifmo.se.client.command;

import ru.ifmo.se.client.Client;
import ru.ifmo.se.general.command.assembler.type.CommandAssembler;

import java.lang.reflect.InvocationTargetException;

public class AssemblerBuilder {
    Class<? extends CommandAssembler> assemblerClass;
    Client client;
    CommandManager manager;

    public AssemblerBuilder(Class<? extends CommandAssembler> assemblerClass) {
        this.assemblerClass = assemblerClass;
    }

    public AssemblerBuilder(Class<? extends CommandAssembler> assemblerClass, Client client) {
        this(assemblerClass);
        this.client = client;
    }

    public AssemblerBuilder(Class<? extends CommandAssembler> assemblerClass, CommandManager manager) {
        this(assemblerClass);
        this.manager = manager;
    }

    public CommandAssembler build() {
        try {
            if (client != null)
                return assemblerClass.getDeclaredConstructor(Client.class).newInstance(client);

            if (manager != null)
                return assemblerClass.getDeclaredConstructor(CommandManager.class).newInstance(manager);

            return assemblerClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
