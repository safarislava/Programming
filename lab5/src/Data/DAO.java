package Data;

import Entities.Organization;

/**
 * Interface of data access object. Allows get info and all organizations, insert, update, remove and load data.
 *
 * @since 1.0
 * @author safarislava
 */
public interface DAO {
    /**
     * @return Collection type
     */
    String type();

    /**
     * @return Count of elements
     */
    int count();

    /**
     * Get all keys from collection
     * @return Array of keys
     */
    String[] getKeys();

    /**
     * @param key Value of key
     * @return Organization by key from collection
     */
    Organization getOrganization(String key);

    /**
     * Insert organization into collection by key.
     *
     * @param key Value of key
     * @param organization Value of organization
     */
    void insert(String key, Organization organization);

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
     * @param key Value of key
     */
    void remove(String key);
}
