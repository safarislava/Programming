package ru.ifmo.se.client.command;

import ru.ifmo.se.general.command.assembler.CommandAssembler;

import java.lang.reflect.InvocationTargetException;

/**
 * Class provides building of Assemblers.
 *
 * @since 3.0
 * @author safarislava
 */
public class AssemblerFactory {
    Class<? extends CommandAssembler> assemblerClass;

    /**
     * Standard constructor.
     *
     * @param assemblerClass Value of class of Assembler
     */
    public AssemblerFactory(Class<? extends CommandAssembler> assemblerClass) {
        this.assemblerClass = assemblerClass;
    }

    /**
     * Build assembler by class-type from constructor.
     *
     * @return Value of Assembler
     */
    public CommandAssembler build() {
        try {
            return assemblerClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
