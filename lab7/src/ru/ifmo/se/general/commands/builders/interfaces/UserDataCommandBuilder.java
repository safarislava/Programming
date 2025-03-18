package ru.ifmo.se.general.commands.builders.interfaces;

import ru.ifmo.se.general.interfaces.UserData;

public interface UserDataCommandBuilder extends ServerNeededCommandBuilder {
    void setUserData(UserData data);
}
