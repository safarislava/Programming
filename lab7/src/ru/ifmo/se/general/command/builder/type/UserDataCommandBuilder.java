package ru.ifmo.se.general.command.builder.type;

import ru.ifmo.se.general.data.UserData;

public interface UserDataCommandBuilder extends ServerNeededCommandBuilder {
    void setUserData(UserData data);
}
