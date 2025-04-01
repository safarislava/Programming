package ru.ifmo.se.server;

import ru.ifmo.se.server.database.DatabaseManager;
import ru.ifmo.se.server.collection.CollectionManager;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("src/main/resources/config/logging.properties"));
        }
        catch (Exception e) {
            System.err.println("Logs config don't found");
        }
        Logger logger = Logger.getLogger(Main.class.getName());

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.connect("jdbc:postgresql://185.239.141.48:5432/studs",
                "src/main/resources/config/db.cfg");

        CollectionManager collectionManager = new CollectionManager(databaseManager);

        logger.info("Starting server");
        Server server = new Server(8012, collectionManager, databaseManager);
        server.start();

        logger.info("Stop server");
    }
}
