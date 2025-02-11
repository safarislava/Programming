package commands.builders;

import commands.Command;

/**
 * Interface of command builder.
 * Allow to set description for command.
 *
 * @since 1.0
 * @author safarislava
 */
public interface CommandBuilder {
    /**
     * @param args Value of arguments these need for command like key or id.
     * @return Built command
     */
    Command build(String[] args);

    /**
     * @return Description string
     */
    String description();
}
