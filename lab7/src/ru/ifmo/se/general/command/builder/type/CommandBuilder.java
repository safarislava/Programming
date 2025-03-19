package ru.ifmo.se.general.command.builder.type;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;

import java.io.Serializable;

/**
 * Interface of command builder.
 * Allow to set description for command.
 *
 * @since 1.0
 * @author safarislava
 */
public interface CommandBuilder extends Serializable {
    /**
     * Build the command
     *
     * @return Built command
     */
    Command build();

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
