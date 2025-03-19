package ru.ifmo.se.general.command;

/**
 * Interface of command. Command must be executing without arguments (see CommandBuilder).
 *
 * @since 1.0
 * @author safarislava
 */
public interface Command {
    /**
     * Method provides work of command
     */
    String execute();
}
