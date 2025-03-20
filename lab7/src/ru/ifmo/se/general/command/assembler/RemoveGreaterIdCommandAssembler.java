package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveGreaterIdCommand;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove greater key command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveGreaterIdCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private int id;

    @Override
    public Command assemble() {
        return new RemoveGreaterIdCommand(id, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");

        id = Integer.parseInt(args[0]);
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
