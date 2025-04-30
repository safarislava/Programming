package ru.ifmo.se.general.exeption;

public class ClosedWindow extends RuntimeException {
    public ClosedWindow() {
        super("Window closed");
    }
}
