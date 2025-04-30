package ru.ifmo.se.client.parser;

import ru.ifmo.se.general.entity.*;
import ru.ifmo.se.general.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
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
     * @param filePath Path of file
     */
    public ScriptParser(Path filePath) {
        File file = filePath.toFile();
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
    public String getString(String prompt) {
        return getLine();
    }

    @Override
    public Organization getOrganization() {
        Organization organization = new Organization();

        organization.setName(getLine());
        Coordinates coordinates = getCoordinates();
        organization.setCoordinates(coordinates);
        organization.setCreationDate();
        organization.setAnnualTurnover(Long.parseLong(getLine()));
        organization.setFullName(getLine());
        organization.setEmployeesCount(Integer.parseInt(getLine()));
        organization.setType(getOrganizationType());
        organization.setPostalAddress(getAddress());

        return organization;
    }

    @Override
    public Organization getOrganizationWithId() {
        return getOrganization();
    }

    @Override
    public Integer getOrganizationId() {
        return Integer.parseInt(getLine());
    }

    public Address getAddress() {
        String zipCode = getLine();
        Location town = getLocation();

        return new Address(zipCode, town);
    }

    public Coordinates getCoordinates() {
        double x = Double.parseDouble(getLine());
        Float y = Float.parseFloat(getLine());

        return new Coordinates(x, y);
    }

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
