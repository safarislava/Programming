package ru.ifmo.se.server.command;

import ru.ifmo.se.general.command.builder.type.UserDataCommandBuilder;
import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.builder.type.CommandBuilder;
import ru.ifmo.se.general.command.builder.type.OrganizationDataCommandBuilder;
import ru.ifmo.se.general.command.builder.type.CreatorSetterCommandBuilder;
import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.data.AuthOrganizationData;
import ru.ifmo.se.general.data.UserData;

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
