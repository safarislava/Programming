package ru.ifmo.se.general.common;

import ru.ifmo.se.general.contract.Response;
import ru.ifmo.se.general.parser.Parser;

public interface ClientInterface {
    Response execute(String command, String[] args);
    void addCall(String call);
    void removeCall(String call);
    Parser getInput();
    void setInput(Parser parser);
    void setUsername(String username);
    void setPassword(String password);
    void stop();
}
