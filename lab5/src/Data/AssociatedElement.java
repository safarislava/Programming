package Data;

import Entities.Organization;

/**
 * Associated element of collection. Needs to conversion collection elements to array.
 *
 * @since 1.0
 * @author safarislava
 */
public class AssociatedElement {
    public String key;
    public Organization organization;

    /**
     * Standard constructor.
     *
     * @param key Value of key
     * @param organization Value of organization
     */
    public AssociatedElement(String key, Organization organization) {
        this.key = key;
        this.organization = organization;
    }
}
