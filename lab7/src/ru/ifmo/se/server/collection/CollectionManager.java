package ru.ifmo.se.server.collection;

import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.entities.Organization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Realisation of DAO. Collection is HashMap.
 *
 * @since 1.0
 * @author safarislava
 */
public class CollectionManager implements OrganizationData {
    private final Map<Integer, Organization> collection = new HashMap<>();
    private final OrganizationData database;
    private final Logger logger = Logger.getLogger(CollectionManager.class.getName());

    /**
     * Standard constructor.
     */
    public CollectionManager(OrganizationData database) {
        this.database = database;
        updateCollection();
    }

    @Override
    public String type() {
        return collection.getClass().getSimpleName();
    }

    @Override
    public int count() {
        return collection.size();
    }

    @Override
    public List<Organization> getOrganizations() {
        LinkedList<Organization> organizations = new LinkedList<>();
        List<Integer> ids = getIds();

        for (Integer id : ids) {
            organizations.add(collection.get(id));
        }

        return organizations;
    }

    @Override
    public List<Integer> getIds() {
        return new LinkedList<>(collection.keySet());
    }

    @Override
    public Organization getOrganization(int id) {
        return collection.get(id);
    }

    @Override
    public String insert(Organization organization, String creator) {
        if (organization.valid()) {
            throw new RuntimeException("Invalid organization");
        }

        logger.info("Inserting an organization");
        String status = database.insert(organization, creator);
        updateCollection();

        return status;
    }

    @Override
    public String update(int id, Organization organization) {
        logger.info("Updating organization: " + id);
        String status = database.update(id, organization);
        updateCollection();

        return status;
    }

    @Override
    public String remove(int id) {
        logger.info("Removing collection: " + id);
        String status = database.remove(id);
        updateCollection();

        return status;
    }

    @Override
    public String getCreator(int id) {
        return database.getCreator(id);
    }

    public void updateCollection() {
        logger.info("Updating collection");

        collection.clear();
        database.getOrganizations().forEach(organization -> collection.put(organization.getId(), organization));
    }
}
