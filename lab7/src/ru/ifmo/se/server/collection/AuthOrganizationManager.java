package ru.ifmo.se.server.collection;

import ru.ifmo.se.general.entities.Organization;
import ru.ifmo.se.general.interfaces.OrganizationData;
import ru.ifmo.se.general.interfaces.AuthOrganizationData;
import ru.ifmo.se.general.interfaces.UserData;

import java.util.List;

public class AuthOrganizationManager implements AuthOrganizationData {
    private final OrganizationData organizationData;
    private final UserData userData;
    private String username;

    public AuthOrganizationManager(OrganizationData organizationData, UserData userData) {
        this.organizationData = organizationData;
        this.userData = userData;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) {
        return userData.checkUserPassword(username, password);
    }

    @Override
    public String type() {
        return organizationData.type();
    }

    @Override
    public int count() {
        return organizationData.count();
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationData.getOrganizations();
    }

    @Override
    public List<Integer> getIds() {
        return organizationData.getIds();
    }

    @Override
    public Organization getOrganization(int id) {
        return organizationData.getOrganization(id);
    }

    @Override
    public String insert(Organization organization, String creator) {
        return organizationData.insert(organization, creator);
    }

    @Override
    public String update(int id, Organization organization) {
        String creator = getCreator(id);
        if (!creator.equals(username)) return String.format("Failed, %s is owner of organization%n", creator);
        return organizationData.update(id, organization);
    }

    @Override
    public String remove(int id) {
        String creator = getCreator(id);
        if (!creator.equals(username)) return String.format("Failed, %s is owner of organization%n", creator);
        return organizationData.remove(id);
    }

    @Override
    public String getCreator(int id) {
        return organizationData.getCreator(id);
    }
}
