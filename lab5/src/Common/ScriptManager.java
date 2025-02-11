package Common;

import Entities.Address;
import Entities.Coordinates;
import Entities.Location;
import Entities.OrganizationType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScriptManager implements Input {
    private final Scanner scanner;

    public ScriptManager(String fileName) {
        File file = new File(fileName + ".sh");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String getLine() {
        return scanner.nextLine();
    }

    @Override
    public boolean hasNext(){
        return scanner.hasNext();
    }

    @Override
    public String[] getCommandArray() {
        String[] commandArray = getLine().split(" ");

        for (int i = 0; i < commandArray.length; i++) {
            if (commandArray[i].isEmpty()) commandArray[i] = null;
        }

        return commandArray;
    }

    @Override
    public Address getAddress() {
        String[] args = getLine().split(" ");

        String zipCode = args[0];
        Location town = getLocation();

        return new Address(zipCode, town);
    }

    @Override
    public Coordinates getCoordinates() {
        String[] args = getLine().split(" ");

        double x = Double.parseDouble(args[0]);
        Float y = Float.parseFloat(args[1]);

        return new Coordinates(x, y);
    }

    @Override
    public Location getLocation() {
        String[] args = getLine().split(" ");

        double x = Double.parseDouble(args[0]);
        Long y = Long.parseLong(args[1]);
        float z = Float.parseFloat(args[2]);

        return new Location(x, y, z);
    }

    @Override
    public OrganizationType getOrganizationType() {
         String line = scanner.nextLine();
         for (OrganizationType organizationType : OrganizationType.values()) {
             if (organizationType.name().equals(line)) {
                 return organizationType;
             }
         }
         throw new RuntimeException("OrganizationType not found");
    }
}
