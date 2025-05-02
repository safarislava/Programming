package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.ShowCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.parser.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling show command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;

    @Override
    public Command assemble() {
        return new ShowCommand(data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Show all elements in the ru.ifmo.se.general.collection";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
