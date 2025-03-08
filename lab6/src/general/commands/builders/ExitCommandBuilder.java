package general.commands.builders;

import client.common.Input;
import general.commands.Command;
import general.commands.ExitCommand;
import client.Client;
import general.commands.builders.interfaces.CommandBuilder;

/**
 * Realisation of CommandBuilder.
 * Provide building exit command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ExitCommandBuilder implements CommandBuilder {
    private final Client client;

    /**
     * Standard constructor.
     *
     * @param client Value of lifecycle class
     */
    public ExitCommandBuilder(Client client) {
        this.client = client;
    }

    @Override
    public Command build() {
        return new ExitCommand(client);
    }

    @Override
    public void setArguments(String[] args, Input input) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description(){
        return "Exit the program";
    }
}
