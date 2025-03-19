package ru.ifmo.se.general;

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

    /**
     * @return Value of organization
     */
    Organization getOrganization();

    /**
     * @return Value of address
     */
    Address getAddress();

    /**
     * @return Value of coordinates
     */
    Coordinates getCoordinates();

    /**
     * @return Value of location
     */
    Location getLocation();

    /**
     * @return Value of organization type
     */
    OrganizationType getOrganizationType();
}
