package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.CountLessTypeCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.OrganizationType;
import ru.ifmo.se.general.common.Parser;

/**
 * Realisation of CommandAssembler.
 * Provide assembling count less type command.
 *
 * @since 1.0
 * @author safarislava
 */
public class CountLessTypeCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private OrganizationType type;

    @Override
    public Command assemble() {
        return new CountLessTypeCommand(type, data);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        if (args.length != 0) throw new IllegalArgumentException("Invalid number of arguments");
        type = parser.getOrganizationType();
        if (type == null) throw new NullPointerException("Invalid type specified");
    }

    @Override
    public String description() {
        return "Print count of organizations which has less types";
    }

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }
}
