package ru.ifmo.se.general.command.assembler;

import ru.ifmo.se.general.command.Command;
import ru.ifmo.se.general.command.UpdateFieldCommand;
import ru.ifmo.se.general.command.assembler.type.OrganizationDataRequired;
import ru.ifmo.se.general.common.Parser;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.OrganizationField;

public class UpdateFieldCommandAssembler implements OrganizationDataRequired {
    private OrganizationData data;
    private int id;
    private OrganizationField field;
    private String value;

    @Override
    public void setOrganizationData(OrganizationData data) {
        this.data = data;
    }

    @Override
    public Command assemble() {
        return new UpdateFieldCommand(data, id, field, value);
    }

    @Override
    public void setArguments(String[] args, Parser parser) {
        id = Integer.parseInt(args[0]);
        field = OrganizationField.valueOf(args[1]);
        value = args[2];
    }

    @Override
    public String description() {
        return "Update field of organization";
    }
}
