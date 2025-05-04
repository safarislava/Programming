package ru.ifmo.se.client.parser;

import ru.ifmo.se.general.entity.*;
import ru.ifmo.se.general.common.Parser;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Realisation of Input. Get info from console.
 *
 * @since 1.0
 * @author safarislava
 */
public class ConsoleParser implements Parser {
    private final Scanner scanner;

    /**
     * Standard constructor
     */
    public ConsoleParser() {
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
    public String getString(String prompt) {
        return getLine(String.format("%s: ", prompt));
    }

    @Override
    public Organization getOrganization() {
        Organization organization = new Organization();

        organization.setCreationDate();

        setOrganizationName(organization);
        setOrganizationCoordinates(organization);
        setOrganizationAnnualTurnover(organization);
        setOrganizationFullName(organization);
        setOrganizationEmployeesCount(organization);
        setOrganizationType(organization);
        setOrganizationAddress(organization);

        return organization;
    }

    @Override
    public Organization getOrganizationWithId() {
        return getOrganization();
    }

    @Override
    public Integer getOrganizationId() {
        return Integer.parseInt(getLine("Id: "));
    }

    private void setOrganizationName(Organization organization) {
        while (true) {
            try {
                String name = getLine("Name: ");
                organization.setName(name);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationCoordinates(Organization organization) {
        while (true) {
            try {
                Coordinates coordinates = getCoordinates();
                organization.setCoordinates(coordinates);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationAnnualTurnover(Organization organization) {
        while (true) {
            try {
                Long annualTurnover = Long.valueOf(getLine("Annual turnover: "));
                organization.setAnnualTurnover(annualTurnover);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationFullName(Organization organization) {
        while (true) {
            try {
                String fullName = getLine("Full name: ");
                organization.setFullName(fullName);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationEmployeesCount(Organization organization) {
        while (true) {
            try {
                int employeesCount = Integer.parseInt(getLine("Employee count: "));
                organization.setEmployeesCount(employeesCount);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationType(Organization organization) {
        while (true) {
            try {
                OrganizationType type = getOrganizationType();
                organization.setType(type);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setOrganizationAddress(Organization organization) {
        while (true) {
            try {
                Address address = getAddress();
                organization.setPostalAddress(address);
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Address getAddress() {
        Address address = new Address();

        String zipCode = getLine("Zip code: ");
        address.setZipCode(zipCode);
        Location town = getLocation();
        address.setTown(town);

        return address;
    }

    public Coordinates getCoordinates() {
        Coordinates coordinates = new Coordinates();

        double x = Double.parseDouble(getLine("Coordinates x: "));
        coordinates.setX(x);
        Float y = Float.parseFloat(getLine("Coordinates y: "));
        coordinates.setY(y);

        return coordinates;
    }

    public Location getLocation() {
        Location location = new Location();

        double x = Double.parseDouble(getLine("Location x: "));
        location.setX(x);
        Long y = Long.parseLong(getLine("Location y: "));
        location.setY(y);
        float z = Float.parseFloat(getLine("Location z: "));
        location.setZ(z);

        return location;
    }

    @Override
    public OrganizationType getOrganizationType() {
        while (true) {
            System.out.printf("Organization types are %s\n", Arrays.toString(OrganizationType.values()));
            System.out.print("Please enter organization type: ");

            String line = getLine("");
            for (OrganizationType organizationType : OrganizationType.values()) {
                if (organizationType.name().equals(line)) {
                    return organizationType;
                }
            }

            System.out.println("No organization type found for name: " + line);
        }
    }
}
