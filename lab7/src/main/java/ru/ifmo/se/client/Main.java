package ru.ifmo.se.client;

import ru.ifmo.se.client.parser.ConsoleParser;

public class Main {
    public static void main(String[] args) {
        ConsoleParser console = new ConsoleParser();

        Client client = new Client("127.0.0.1", 8012, console); // "185.239.141.48"

        client.start();
    }
}