package common;

import commands.CommandController;
import collection.OrganizationDAO;

import java.util.Arrays;
import java.util.Set;

/**
 * Major class. Provides life cycle of program.
 * Wait for command and transit to CommandManager for recognizing.
 *
 * @since 1.0
 * @author safarislava
 */
public class Client {
    private boolean running = false;
    private final Input input;
    private final CommandController commandController;

    /**
     * Standard constructor.
     *
     * @param data Value of data access object
     */
    public Client(OrganizationDAO data, Input input, Set<String> scriptCalls) {
        this.input = input;
        commandController = new CommandController(this, data, input, scriptCalls);
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

    public void showText(String text) {
        System.out.print(text);
    }
}
