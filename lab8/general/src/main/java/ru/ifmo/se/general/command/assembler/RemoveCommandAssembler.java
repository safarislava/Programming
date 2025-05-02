package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.RemoveCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling remove command.
 *
 * @since 1.0
 * @author safarislava
 */
public class RemoveCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private Integer id;

    @Override
    public Command assemble() {

        return new RemoveCommand(data, id);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        id = parser.getOrganizationId();
        if (id == null) throw new NullPointerException("Organization id is null");
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
