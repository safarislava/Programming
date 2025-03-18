package ru.ifmo.se.general.commands.builders.interfaces;

import ru.ifmo.se.general.interfaces.OrganizationData;

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
