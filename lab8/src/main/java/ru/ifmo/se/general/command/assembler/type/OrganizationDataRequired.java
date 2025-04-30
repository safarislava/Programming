package ru.ifmo.se.general.command.assembler.type;

import ru.ifmo.se.general.data.OrganizationData;

/**
 * Interface of command assembler.
 * Allow to set data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public interface OrganizationDataRequired extends ServerRequired {
    void setOrganizationData(OrganizationData data);
}
