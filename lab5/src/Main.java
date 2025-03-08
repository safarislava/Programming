import common.CsvManager;
import common.ConsoleManager;
import common.Client;
import collection.CollectionManager;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        CsvManager csvManager = new CsvManager();
        ConsoleManager console = new ConsoleManager();

        CollectionManager collectionManager = new CollectionManager();

        if (args.length >= 1) {
            collectionManager.load(csvManager.load(args[0]));
        }

        Client client = new Client(collectionManager, console, new HashSet<>());
        client.start();
    }
}