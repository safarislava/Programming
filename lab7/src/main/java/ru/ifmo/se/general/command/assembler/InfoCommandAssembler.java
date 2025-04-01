package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.InfoCommand;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;

/**
 * Realisation of CommandAssembler.
 * Provide assembling info command.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;

    @Override
    public Command assemble() {
        return new InfoCommand(data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Displays information type of general.collection and count of elements";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
