package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveLowerCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove lower command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveLowerCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private Organization organization;

    @Override
    public Command assemble() {
        return new RemoveLowerCommand(organization, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");

        organization = parser.getOrganization();
        if (organization == null) throw new NullPointerException("Organization is null");
    }

    @Override
    public String description() {
        return "Remove all lower organizations";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
