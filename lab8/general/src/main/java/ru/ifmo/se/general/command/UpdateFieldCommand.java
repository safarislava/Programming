package ru.ifmo.se.general.command;

import ru.ifmo.se.general.contract.CodePhrase;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.entity.Organization;
import ru.ifmo.se.general.entity.OrganizationField;
import ru.ifmo.se.general.entity.OrganizationType;

public class UpdateFieldCommand implements Command {
    private final OrganizationData data;
    private final int id;
    private final OrganizationField field;
    private final String value;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param id Value of id
     */
    public UpdateFieldCommand(OrganizationData data, int id, OrganizationField field, String value) {
        this.data = data;
        this.id = id;
        this.field = field;
        this.value = value;
    }

    @Override
    public String execute() {
        Organization organization = data.getOrganization(id);

        try {
            switch (field) {
                case NAME -> organization.setName(value);
                case COORDINATE_X -> organization.getCoordinates().setX(Double.parseDouble(value));
                case COORDINATE_Y -> organization.getCoordinates().setY(Float.parseFloat(value));
                case ANNUAL_TURNOVER -> organization.setAnnualTurnover(Long.valueOf(value));
                case TYPE -> organization.setType(OrganizationType.valueOf(value));
                case ZIPCODE -> organization.getPostalAddress().setZipCode(value);
                case TOWN_X -> organization.getPostalAddress().getTown().setX(Double.parseDouble(value));
                case TOWN_Y -> organization.getPostalAddress().getTown().setY(Long.valueOf(value));
                case TOWN_Z -> organization.getPostalAddress().getTown().setZ(Float.parseFloat(value));
                default -> {
                    return CodePhrase.FAILED;
                }
            }
        }
        catch (Exception e) {return CodePhrase.FAILED;}

        return data.update(id, organization);
    }
}
