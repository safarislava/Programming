package collection;

import entities.Organization;

/**
 * Interface of data access object. Allows get info and all organizations, insert, update, remove and load data.
 *
 * @since 1.0
 * @author safarislava
 */
public interface OrganizationDAO {
    /**
     * @return Collection type
     */
    String type();

    /**
     * @return Count of elements
     */
    int count();

    /**
     * Get all organizations
     *
     * @return Array of organizations
     */
    Organization[] getOrganizations();

    /**
     * Get all ids from collection
     *
     * @return Array of ids
     */
    Integer[] getIds();

    /**
     * @param id Value of id
     * @return Organization by key from collection
     */
    Organization getOrganization(int id);

    /**
     * Insert organization into collection by key.
     *
     * @param organization Value of organization
     */
    void insert(Organization organization);

    /**
     * Copy fields from param organization to one from collection by id.
     *
     * @param id Value of id
     * @param organization Value of organization
     */
    void update(int id, Organization organization);

    /**
     * Remove organization from collection by key.
     *
     * @param id Value of key
     */
    void remove(int id);
}
