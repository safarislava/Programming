package server;

import server.collection.CollectionManager;
import server.common.CsvManager;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logs/logging.properties"));
        }
        catch (Exception e) {
            System.out.println("Logs config don't found");
        }

        Logger logger = Logger.getLogger(Main.class.getName());

        String fileName = "test/collection";
        CsvManager csvManager = new CsvManager(fileName);
        CollectionManager collectionManager = new CollectionManager();
        logger.info("Load collection from file");
        collectionManager.load(csvManager.load());

        logger.info("Starting server");
        Server server = new Server(8012, collectionManager);
        server.start();

        logger.info("Save collection to file");
        csvManager.save(collectionManager.getOrganizations());
    }
}
