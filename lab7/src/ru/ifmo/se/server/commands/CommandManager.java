package ru.ifmo.se.server.commands;

import ru.ifmo.se.general.commands.builders.interfaces.UserDataCommandBuilder;
import ru.ifmo.se.general.contracts.Request;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.CreatorSetterCommandBuilder;
import ru.ifmo.se.general.interfaces.AuthOrganizationData;
import ru.ifmo.se.general.interfaces.UserData;

/**
 * Class providing command executing.
 *
 * @since 2.0
 * @author safarislava
 */
public class CommandManager {
    private final AuthOrganizationData authOrganizationData;
    private final UserData userData;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object.
     */
    public CommandManager(AuthOrganizationData authOrganizationData, UserData userData) {
        this.authOrganizationData = authOrganizationData;
        this.userData = userData;
    }

    /**
     * Set different needing arguments for CommandBuilder.
     * Support DataCommandBuilder and IdSetterCommandBuilder.
     *
     * @param builder Value of builder
     */
    private void setArguments(Request request) {
        CommandBuilder builder = request.commandBuilder;

        if (builder instanceof OrganizationDataCommandBuilder) {
            ((OrganizationDataCommandBuilder) builder).setOrganizationData(authOrganizationData);
        }
        if (builder instanceof CreatorSetterCommandBuilder) {
            ((CreatorSetterCommandBuilder) builder).setCreator(request.username);
        }
        if (builder instanceof UserDataCommandBuilder) {
            ((UserDataCommandBuilder) builder).setUserData(userData);
        }
    }

    /**
     * Build and execute command.
     *
     * @param builder Value of builder.
     * @return String of response.
     */
    public String execute(Request request) {
        authOrganizationData.setUsername(request.username);
        if (!authOrganizationData.checkPassword(request.password)) return "Incorrect login or password\n";

        setArguments(request);
        Command command = request.commandBuilder.build();

        return command.execute();
    }
}
