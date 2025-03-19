package ru.ifmo.se.general.data;

public interface AuthOrganizationData extends OrganizationData {
    void setUsername(String username);

    boolean checkPassword(String password);
}
