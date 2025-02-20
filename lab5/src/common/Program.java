package common;

import commands.CommandController;
import data.OrganizationDAO;

import java.util.Arrays;

/**
 * Major class. Provides life cycle of program.
 * Wait for command and transit to CommandManager for recognizing.
 *
 * @since 1.0
 * @author safarislava
 */
public class Program {
    private boolean running = false;
    private final Input input;
    private final CommandController commandController;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public Program(OrganizationDAO data, Input input) {
        this.input = input;
        commandController = new CommandController(this, data, input);
    }

    /**
     * Start listening input for command
     */
    public void start() {
        running = true;

        while (running && input.hasNext()) {
            String[] commandArray = input.getCommandArray();
            String command = commandArray[0];
            String[] args = Arrays.copyOfRange(commandArray, 1, commandArray.length);

            try {
                commandController.execute(command, args);
            }
            catch (Exception e) {
                System.out.printf("Error: %s%n", e.getMessage());
            }
        }
    }

    /**
     * Stop listening
     */
    public void stop() {
        running = false;
    }
}
