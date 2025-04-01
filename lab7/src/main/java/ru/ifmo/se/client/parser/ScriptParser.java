package ru.ifmo.se.client.parser;

import ru.ifmo.se.general.entity.*;
import ru.ifmo.se.general.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that provide read scripts from file/
 *
 * @since 1.0
 * @author safarislava
 */
public class ScriptParser implements Parser {
    private final Scanner scanner;

    /**
     * Standard constructor
     *
     * @param fileName Value of file name
     */
    public ScriptParser(String fileName) {
        File file = new File(fileName + ".sh");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Value of next line
     */
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
    public Organization getOrganization() {
        String name = getLine();
        Coordinates coordinates = getCoordinates();
        long annualTurnover = Long.parseLong(getLine());
        String fullName = getLine();
        int employeesCount = Integer.parseInt(getLine());
        OrganizationType type = getOrganizationType();
        Address address = getAddress();

        return new Organization(name, coordinates, annualTurnover, fullName, employeesCount, type, address);
    }

    @Override
    public Address getAddress() {
        String zipCode = getLine();
        Location town = getLocation();

        return new Address(zipCode, town);
    }

    @Override
    public Coordinates getCoordinates() {
        double x = Double.parseDouble(getLine());
        Float y = Float.parseFloat(getLine());

        return new Coordinates(x, y);
    }

    @Override
    public Location getLocation() {
        double x = Double.parseDouble(getLine());
        Long y = Long.parseLong(getLine());
        float z = Float.parseFloat(getLine());

        return new Location(x, y, z);
    }

    @Override
    public OrganizationType getOrganizationType() {
         String line = getLine();
         for (OrganizationType organizationType : OrganizationType.values()) {
             if (organizationType.name().equals(line)) {
                 return organizationType;
             }
         }
         throw new RuntimeException("Exception: OrganizationType not found");
    }
}
