package server.collection;

import general.collection.OrganizationData;
import general.entities.Organization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Realisation of DAO. Collection is HashMap.
 *
 * @since 1.0
 * @author safarislava
 */
public class CollectionManager implements OrganizationData {
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
    public List<Organization> getOrganizations() {
        LinkedList<Organization> organizations = new LinkedList<>();
        List<Integer> ids = getIds();

        for (Integer id : ids) {
            organizations.add(database.get(id));
        }

        return organizations;
    }

    @Override
    public List<Integer> getIds() {
        return new LinkedList<>(database.keySet());
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
     * Load or rewrite existed organizations into general.collection
     *
     * @param organizations Array of organizations
     */
    public void load(Organization[] organizations) {
        for (Organization organization : organizations) {
            database.put(organization.getId(), organization);
        }
    }
}
