package ru.ifmo.se.general.data;

/**
 * Interface for authorizing under OrganizationData.
 *
 * @since 3.0
 * @author safarislava
 */
public interface AuthOrganizationData extends OrganizationData {
    /**
     * Setter of username
     *
     * @param username Value of username
     */
    void setUsername(String username);

    /**
     * Validate password.
     *
     * @param password Value of string
     * @return True if password is correct
     */
    boolean checkPassword(String password);
}
