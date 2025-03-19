package ru.ifmo.se.general.command.builder.type;

import ru.ifmo.se.general.data.OrganizationData;

/**
 * Interface of command builder.
 * Allow to set data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public interface OrganizationDataCommandBuilder extends ServerNeededCommandBuilder {
    void setOrganizationData(OrganizationData data);
}
