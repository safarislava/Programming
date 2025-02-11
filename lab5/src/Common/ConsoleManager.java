package Common;

import Entities.Address;
import Entities.Coordinates;
import Entities.Location;
import Entities.OrganizationType;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Realisation of Input. Get info from console.
 *
 * @since 1.0
 * @author safarislava
 */
public class ConsoleManager implements Input {
    private final Scanner scanner;

    /**
     * Standard constructor
     */
    public ConsoleManager() {
        scanner = new Scanner(System.in);
    }

    /**
     * Provides getting line from console with prompt.
     *
     * @param prompt Value of prompt
     * @return Value of user output
     */
    private String getLine(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();

        line = line.trim();

        return line;
    }

    @Override
    public boolean hasNext(){
        return true;
    }

    @Override
    public String[] getCommandArray(){
        String[] commandArray = getLine("Command -> ").split(" ");

        for (int i = 0; i < commandArray.length; i++) {
            if (commandArray[i].isEmpty()) commandArray[i] = null;
        }

        return commandArray;
    }

    @Override
    public Address getAddress() {
        String[] args = getLine("Please enter address (zipCode): ").split(" ");

        String zipCode = args[0];
        Location town = getLocation();

        return new Address(zipCode, town);
    }

    @Override
    public Coordinates getCoordinates() {
        String[] args = getLine("Please enter coordinates (x, y): ").split(" ");

        double x = Double.parseDouble(args[0]);
        Float y = Float.parseFloat(args[1]);

        return new Coordinates(x, y);
    }

    @Override
    public Location getLocation() {
        String[] args = getLine("Please enter location (x, y, z): ").split(" ");

        double x = Double.parseDouble(args[0]);
        Long y = Long.parseLong(args[1]);
        float z = Float.parseFloat(args[2]);

        return new Location(x, y, z);
    }

    @Override
    public OrganizationType getOrganizationType() {
        while (true) {
            System.out.printf("Organization types are %s\n", Arrays.toString(OrganizationType.values()));
            System.out.print("Please enter organization type: ");

            String line = scanner.nextLine();
            for (OrganizationType organizationType : OrganizationType.values()) {
                if (organizationType.name().equals(line)) {
                    return organizationType;
                }
            }

            System.out.println("No organization type found for name: " + line);
        }
    }
}
