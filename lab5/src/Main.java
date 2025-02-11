import Common.CsvManager;
import Common.ConsoleManager;
import Common.Program;
import Data.Database;

public class Main {
    public static void main(String[] args) {
        CsvManager conserve = new CsvManager();
        ConsoleManager console = new ConsoleManager();

        Database database = new Database();
        database.load(conserve.load(args[0]));

        Program program = new Program(database, console);
        program.start();
    }
}