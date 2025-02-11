package Commands;

import Data.DAO;

/**
 * Realisation of CommandBuilder.
 * Provide printing info about data access object.
 *
 * @since 1.0
 * @author safarislava
 */
public class InfoCommand implements Command {
    private final DAO data;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public InfoCommand(DAO data) {
        this.data = data;
    }

    @Override
    public void execute() {
        System.out.printf("%s %s%n", data.type(), data.count());
    }
}
