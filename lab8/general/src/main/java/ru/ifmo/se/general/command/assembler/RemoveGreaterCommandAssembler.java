package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveGreaterCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove greater command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command assemble() {
        return new RemoveGreaterCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = parser.getOrganization();
        if (organization == null) throw new NullPointerException("Organization is null");
    }

    @Override
    public String description() {
        return "Remove all greater organizations";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
