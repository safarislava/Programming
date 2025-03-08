package general.commands.builders;

import client.common.Input;
import general.commands.Command;

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
     * @param args Value of arguments these need for command like key or id.
     * @return Built command
     */
    Command build();

    void setArguments(String[] args, Input input);

    /**
     * @return Description string
     */
    String description();
}
