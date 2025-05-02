package ru.ifmo.se.general.command;

import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.contract.OrganizationConverter;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.entity.OrganizationDto;

import java.util.List;

/**
 * Realisation of CommandAssembler.
 * Provide printing all organizations.
 *
 * @since 1.0
 * @author safarislava
 */
public class ShowCommand implements Command {
    private final OrganizationData data;

    /**
     * Standard constructor.
     *
     * @param data    Value of data access object
     */
    public ShowCommand(OrganizationData data) {
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();
        List<Organization> organizations = data.getOrganizations();

        organizations.forEach(o -> text.append(OrganizationConverter.encode(
                new OrganizationDto(o, data.getCreator(o.getId())))));

        if (text.isEmpty()) {
            return CodePhrase.SKIP;
        }

        return text.toString();
    }
}
