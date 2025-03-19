package ru.ifmo.se.server.commands;

import ru.ifmo.se.general.commands.builders.interfaces.UserDataCommandBuilder;
import ru.ifmo.se.general.contracts.Request;
import ru.ifmo.se.general.commands.Command;
import ru.ifmo.se.general.commands.builders.interfaces.CommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.commands.builders.interfaces.CreatorSetterCommandBuilder;
import ru.ifmo.se.general.contracts.Response;
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
     * @param authOrganizationData Value of authOrganizationData access object.
     * @param userData Value of userData
     */
    public CommandManager(AuthOrganizationData authOrganizationData, UserData userData) {
        this.authOrganizationData = authOrganizationData;
        this.userData = userData;
    }

    /**
     * Set different needing arguments for CommandBuilder.
     * Support DataCommandBuilder and IdSetterCommandBuilder.
     *
     * @param request Value of request
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
     * @param request Value of request.
     * @return String of response.
     */
    public Response execute(Request request) {
        authOrganizationData.setUsername(request.username);
        if (!authOrganizationData.checkPassword(request.password))
            return new Response("Incorrect login or password\n");

        setArguments(request);
        Command command = request.commandBuilder.build();
        String result = command.execute();

        return new Response(result);
    }
}
