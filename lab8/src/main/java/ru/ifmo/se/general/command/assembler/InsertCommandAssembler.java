package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.InsertCommand;
import ru.ifmo.se.general.parser.Parser;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.command.assembler.type.CreatorRequired;
import ru.ifmo.se.general.entity.Organization;

/**
 * Realisation of CommandAssembler.
 * Provide assembling insert command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InsertCommandAssembler implements OrganizationDataRequired, CreatorRequired {
    private OrganizationData data;
    private Organization organization;
    private String creator;

    @Override
    public Command assemble() {
        return new InsertCommand(data, organization, creator);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        organization = parser.getOrganization();
        if (organization == null) throw new NullPointerException("Organization is null");
    }

    @Override
    public String description() {
        return "Add new organization";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
}
