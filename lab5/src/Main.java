import common.CsvManager;
import common.ConsoleManager;
import common.Program;
import data.Database;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        CsvManager conserve = new CsvManager();
        ConsoleManager console = new ConsoleManager();

        Database database = new Database();
        database.load(conserve.load(args[0]));

        Program program = new Program(database, console, new HashSet<>());
        program.start();
    }
}