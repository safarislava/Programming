package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveCommand;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataCommandAssembler;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommandAssembler implements OrganizationDataCommandAssembler {
    private OrganizationData data;
    private int id;

    @Override
    public Command assemble() {

        return new RemoveCommand(data, id);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 1) throw new IllegalArgumentException("Invalid number of arguments");
        id = Integer.parseInt(args[0]);
    }

    @Override
    public String description() {
        return "Remove element by id";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
