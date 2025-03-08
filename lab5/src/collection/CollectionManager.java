package collection;

import entities.Organization;

import java.util.HashMap;


/**
 * Realisation of DAO. Collection is HashMap.
 *
 * @since 1.0
 * @author safarislava
 */
public class CollectionManager implements OrganizationDAO {
    private final HashMap<Integer, Organization> database;

    /**
     * Standard constructor.
     */
    public CollectionManager() {
        database = new HashMap<>();
    }

    @Override
    public String type() {
        return database.getClass().getSimpleName();
    }

    @Override
    public int count() {
        return database.size();
    }

    @Override
    public Organization[] getOrganizations() {
        Organization[] organizations = new Organization[database.size()];
        Integer[] ids = getIds();

        for (int i = 0; i < database.size(); i++) {
            organizations[i] = getOrganization(ids[i]);
        }

        return organizations;
    }

    @Override
    public Integer[] getIds() {
        return database.keySet().toArray(new Integer[0]);
    }

    @Override
    public Organization getOrganization(int id) {
        return database.get(id);
    }

    @Override
    public void insert(Organization organization) {
        if (organization.valid()) {
            throw new RuntimeException("Invalid organization");
        }

        database.put(organization.getId(), organization);
    }

    @Override
    public void update(int id, Organization organization) {
        for (Organization o : database.values()) {
            if (o.getId() == id) {
                o.copy(organization);
                break;
            }
        }
    }

    @Override
    public void remove(int id) {
        database.remove(id);
    }

    /**
     * Load or rewrite existed organizations into collection
     *
     * @param organizations Array of organizations
     */
    public void load(Organization[] organizations) {
        for (Organization organization : organizations) {
            database.put(organization.getId(), organization);
        }
    }
}
