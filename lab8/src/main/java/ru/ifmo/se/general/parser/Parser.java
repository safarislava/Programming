package ru.ifmo.se.general.parser;

import ru.ifmo.se.general.entity.*;

/**
 * Interface for asking missing information
 *
 * @since 1.0
 * @author safarislava
 */
public interface Parser {
    /**
     * @return True if it makes sense to wait for response
     */
    boolean hasNext();

    /**
     * @return String array of command and arguments
     */
    String[] getCommandArray();

    String getString(String prompt);

    /**
     * @return Value of organization
     */
    Organization getOrganization();

    Organization getOrganizationWithId();

    Integer getOrganizationId();

    /**
     * @return Value of organization type
     */
    OrganizationType getOrganizationType();
}
