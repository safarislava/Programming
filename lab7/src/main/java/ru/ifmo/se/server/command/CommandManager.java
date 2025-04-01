package ru.ifmo.se.server.command;

import ru.ifmo.se.general.command.assembler.type.UserDataRequired;
import ru.ifmo.se.general.contract.Request;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.assembler.CommandAssembler;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.command.assembler.type.CreatorRequired;
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
        CommandAssembler assembler = request.commandAssembler;

        if (assembler instanceof OrganizationDataRequired) {
            ((OrganizationDataRequired) assembler).setOrganizationData(authOrganizationData);
        }
        if (assembler instanceof CreatorRequired) {
            ((CreatorRequired) assembler).setCreator(request.username);
        }
        if (assembler instanceof UserDataRequired) {
            ((UserDataRequired) assembler).setUserData(userData);
        }
    }

    /**
     * Build and execute command.
     *
     * @param request Value of request.
     * @return String of response.
     */
    public Response execute(Request request) {
        if (request.commandAssembler == null) return new Response();

        authOrganizationData.setUsername(request.username);
        if (!authOrganizationData.checkPassword(request.password))
            return new Response("Incorrect login or password\n");

        setArguments(request);
        Command command = request.commandAssembler.assemble();
        String result = command.execute();

        return new Response(result);
    }
}
