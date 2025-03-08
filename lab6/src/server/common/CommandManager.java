package server.common;

import general.collection.OrganizationData;
import general.commands.Command;
import general.commands.builders.interfaces.CommandBuilder;
import general.commands.builders.interfaces.DataCommandBuilder;
import general.commands.builders.interfaces.IdSetterCommandBuilder;
import general.entities.Organization;

/**
 * Class providing command executing.
 *
 * @since 2.0
 * @author safarislava
 */
public class CommandManager {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object.
     */
    public CommandManager(OrganizationData data) {
        this.data = data;
    }

    /**
     * Set different needing arguments for CommandBuilder.
     * Support DataCommandBuilder and IdSetterCommandBuilder.
     *
     * @param builder Value of builder
     */
    private void setArguments(CommandBuilder builder) {
        if (builder instanceof DataCommandBuilder) {
            ((DataCommandBuilder) builder).setData(data);
        }
        if (builder instanceof IdSetterCommandBuilder) {
            ((IdSetterCommandBuilder) builder).setId(Organization.getUniqueId());
        }
    }

    /**
     * Build and execute command.
     *
     * @param builder Value of builder.
     * @return String of response.
     */
    public String execute(CommandBuilder builder) {
        setArguments(builder);

        Command command = builder.build();

        return command.execute();
    }
}
