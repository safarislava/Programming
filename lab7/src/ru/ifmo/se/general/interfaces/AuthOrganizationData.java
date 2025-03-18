package ru.ifmo.se.general.interfaces;

public interface AuthOrganizationData extends OrganizationData {
    void setUsername(String username);

    boolean checkPassword(String password);
}
