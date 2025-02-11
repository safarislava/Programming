package Data;

import Entities.Organization;

import java.util.HashMap;


/**
 * Realisation of DAO. Collection is HashMap.
 *
 * @since 1.0
 * @author safarislava
 */
public class Database implements DAO {
    private final HashMap<String, Organization> database;

    /**
     * Standard constructor.
     */
    public Database() {
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
    public String[] getKeys() {
        return database.keySet().toArray(new String[0]);
    }

    @Override
    public Organization getOrganization(String key) {
        return database.get(key);
    }

    @Override
    public void insert(String key, Organization organization){
        if (organization.valid()) {
            throw new RuntimeException("Invalid organization");
        }

        database.put(key, organization);
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
    public void remove(String key) {
        database.remove(key);
    }

    public void load(AssociatedElement[] elements) {
        for (AssociatedElement element : elements) {
            database.put(element.key, element.organization);
        }
    }
}
