package ru.ifmo.se.general.interfaces;

public interface UserData {
    void register(String user, String password);
    boolean checkUserPassword(String username, String password);
}
