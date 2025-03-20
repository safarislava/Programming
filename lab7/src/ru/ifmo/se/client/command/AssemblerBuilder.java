package ru.ifmo.se.client.command;

import ru.ifmo.se.general.command.assembler.CommandAssembler;

import java.lang.reflect.InvocationTargetException;

public class AssemblerBuilder {
    Class<? extends CommandAssembler> assemblerClass;

    public AssemblerBuilder(Class<? extends CommandAssembler> assemblerClass) {
        this.assemblerClass = assemblerClass;
    }

    public CommandAssembler build() {
        try {
            return assemblerClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
