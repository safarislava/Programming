package Common;

import Entities.Address;
import Entities.Coordinates;
import Entities.Location;
import Entities.OrganizationType;

/**
 * Interface for asking missing information
 *
 * @since 1.0
 * @author safarislava
 */
public interface Input {
    /**
     * @return True if it makes sense to wait for response
     */
    boolean hasNext();

    /**
     * @return String array of command and arguments
     */
    String[] getCommandArray();

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
