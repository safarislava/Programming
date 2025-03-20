package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.Parser;
import ru.ifmo.se.general.command.ClearCommand;
import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;

/**
 * Realisation of CommandAssembler.
 * Provide assembling clear command.
 *
 * @since 1.0
 * @author safarislava
 */
public class ClearCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;

    @Override
    public Command assemble() {
        return new ClearCommand(data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
    }

    @Override
    public String description() {
        return "Clear general.collection";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
