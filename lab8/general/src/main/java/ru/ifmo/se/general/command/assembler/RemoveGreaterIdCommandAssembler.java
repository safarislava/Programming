package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveGreaterIdCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private Integer id;

    @Override
    public Command assemble() {
        return new RemoveGreaterIdCommand(id, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        id = parser.getOrganizationId();
        if (id == null) throw new NullPointerException("Organization id is null");
    }

    @Override
    public String description() {
        return "Remove all organizations, which have a greater id";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
