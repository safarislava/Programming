package ru.ifmo.se.general.data;

/**
 * Interface for users data access. Allow registering and checking password.
 *
 * @since 3.0
 * @author safarislava
 */
public interface UserData {
    /**
     * Method provides register new user.
     *
     * @param username Value of username
     * @param password Value of password
     * @return State of registration
     */
    String register(String username, String password);

    /**
     * Method provides checking a password for user.
     *
     * @param username Value of username
     * @param password Value of password
     * @return True if correct
     */
    boolean checkUserPassword(String username, String password);
}
