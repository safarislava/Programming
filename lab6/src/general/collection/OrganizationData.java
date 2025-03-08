package general.collection;

import general.entities.Organization;

import java.util.List;

/**
 * Interface of data access object. Allows get info and all organizations, insert, update, remove and load data.
 *
 * @since 1.0
 * @author safarislava
 */
public interface OrganizationData {
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
     * @return List of organizations
     */
    List<Organization> getOrganizations();

    /**
     * Get all ids from collection
     *
     * @return List of ids
     */
    List<Integer> getIds();

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
