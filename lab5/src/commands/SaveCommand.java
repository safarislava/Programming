package commands;

import common.CsvManager;
import data.OrganizationDAO;
import entities.Organization;

/**
 * Realisation of CommandBuilder.
 * Provide saving data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class SaveCommand implements Command {
    private final OrganizationDAO data;
    private final String fileName;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     * @param filename Value of file name
     */
    public SaveCommand(OrganizationDAO data, String filename) {
        this.data = data;
        this.fileName = filename;
    }

    @Override
    public void execute() {
        Organization[] organizations = data.getOrganizations();

        CsvManager conserve = new CsvManager();
        conserve.save(organizations, fileName);
    }
}
