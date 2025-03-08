package client;

import client.common.ConsoleManager;

public class Main {
    public static void main(String[] args) {
        ConsoleManager console = new ConsoleManager();

        Client client = new Client("helios.se.ifmo.ru", 8012, console);

        client.start();
    }
}