package client;

import client.common.ConsoleManager;

public class Main {
    public static void main(String[] args) {
        ConsoleManager console = new ConsoleManager();

        Client client = new Client("185.239.141.48", 8012, console); // "helios.se.ifmo.ru"

        client.start();
    }
}