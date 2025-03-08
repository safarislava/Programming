package server;

import server.collection.CollectionManager;
import server.common.CsvManager;

public class Main {
    public static void main(String[] args) {
        String fileName = "collection";

        CsvManager csvManager = new CsvManager(fileName);
        CollectionManager collectionManager = new CollectionManager();
        collectionManager.load(csvManager.load());

        Server server = new Server(8012, collectionManager);
        server.start();

        csvManager.save(collectionManager.getOrganizations());
    }
}
