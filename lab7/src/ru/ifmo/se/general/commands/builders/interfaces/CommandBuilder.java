package ru.ifmo.se.general.commands.builders.interfaces;

import ru.ifmo.se.general.interfaces.Input;
import ru.ifmo.se.general.commands.Command;

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
     * @param input Value of input
     */
    void setArguments(String[] args, Input input);

    /**
     * @return Description string
     */
    String description();
}
