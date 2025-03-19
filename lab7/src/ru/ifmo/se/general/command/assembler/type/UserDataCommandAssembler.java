package ru.ifmo.se.general.command.assembler.type;

import ru.ifmo.se.general.data.UserData;

public interface UserDataCommandAssembler extends ServerNeededCommandAssembler {
    void setUserData(UserData data);
}
