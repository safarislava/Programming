package Commands;

import Common.CsvManager;
import Data.DAO;
import Data.AssociatedElement;

/**
 * Realisation of CommandBuilder.
 * Provide saving data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class SaveCommand implements Command {
    private final DAO data;
    private final String fileName;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param filename Value of file name
     */
    public SaveCommand(DAO data, String filename) {
        this.data = data;
        this.fileName = filename;
    }

    @Override
    public void execute() {
        String[] keys = data.getKeys();

        AssociatedElement[] elements = new AssociatedElement[keys.length];
        for (int i = 0; i < keys.length; i++) {
            elements[i] = new AssociatedElement(keys[i], data.getOrganization(keys[i]));
        }

        CsvManager conserve = new CsvManager();
        conserve.save(elements, fileName);
    }
}
