package ru.ifmo.se.general.command.assembler.type;

import ru.ifmo.se.general.data.UserData;

public interface UserDataRequired extends ServerRequired {
    void setUserData(UserData data);
}
