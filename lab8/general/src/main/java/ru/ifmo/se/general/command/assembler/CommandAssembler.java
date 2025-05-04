package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.common.Parser;

import java.io.Serializable;

/**
 * Interface of command assembler.
 * Allow to set description for command.
 *
 * @since 1.0
 * @author safarislava
 */
public interface CommandAssembler extends Serializable {
    /**
     * Build the command
     *
     * @return Built command
     */
    Command assemble();

    /**
     * Set arguments that contains in line with command or input after.
     *
     * @param args Array of strings
     * @param parser Value of input
     */
    void setArguments(String[] args, Parser parser);

    /**
     * @return Description string
     */
    String description();
}
