package ru.ifmo.se.general.command;

import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.contract.OrganizationConverter;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.entity.OrganizationDto;

import java.util.List;

/**
 * Realisation of CommandAssembler.
 * Provide printing organizations that has same full name as entered
 *
 * @since 1.0
 * @author safarislava
 */
public class FilterFullNameCommand implements Command {
    private final OrganizationData data;
    private final String fullName;

    /**
     * Standard constructor.
     *
     * @param fullName Value of full name
     * @param data     Value of data access object
     */
    public FilterFullNameCommand(String fullName, OrganizationData data) {
        this.fullName = fullName;
        this.data = data;
    }

    @Override
    public String execute() {
        StringBuilder text = new StringBuilder();

        List<Organization> organizations = data.getOrganizations();

        organizations.stream().filter(o -> o.getFullName().equals(fullName))
                .forEach(o -> text.append(OrganizationConverter.encode(
                        new OrganizationDto(o, data.getCreator(o.getId())))));

        if (text.isEmpty()) {
            return CodePhrase.SKIP;
        }

        return text.toString();
    }
}

