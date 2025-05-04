package ru.ifmo.se.general.common;

import ru.ifmo.se.general.contract.Response;

import java.nio.file.Path;

public interface AbstractClient {
    Response execute(String command, String[] args);
    void addCall(String call);
    void removeCall(String call);
    Parser getInput();
    Parser getInput(Path script);
    void setInput(Parser parser);
    void setUsername(String username);
    void setPassword(String password);
    void start();
    void stop();
}
