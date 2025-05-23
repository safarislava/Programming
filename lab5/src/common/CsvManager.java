package common;

import entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class provides interaction with csv files.
 *
 * @since 1.0
 * @author safarislava
 */
public class CsvManager {
    /**
     * Save collection to csv file
     *
     * @param organizations Array of organizations
     * @param fileName Value of file name
     */
    public void save(Organization[] organizations, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName + ".csv", false);
            OutputStreamWriter output = new OutputStreamWriter(file);

            StringBuilder data = new StringBuilder();
            for (Organization o : organizations) {
                Coordinates coordinates = o.getCoordinates();
                Address address = o.getPostalAddress();
                Location town = address.getTown();

                data.append(convertCSV(o.getId(), o.getName(), coordinates.getX(), coordinates.getY(),
                        o.getCreationDate(), o.getAnnualTurnover(), o.getFullName(), o.getEmployeesCount(),
                        o.getType(), address.getZipCode(), town.getX(), town.getY(), town.getZ()));
            }
            output.write(data.toString());
            output.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Convert objects to CSV line
     *
     * @param objects Value of objects
     * @return Line describing collection element
     */
    private String convertCSV(Object... objects) {
        StringBuilder data = new StringBuilder();

        Iterator<Object> iterator = Arrays.asList(objects).iterator();
        while (iterator.hasNext()) {
            data.append(iterator.next().toString());

            if (iterator.hasNext()) {
                data.append(",");
            }
            else {
                data.append("\n");
            }
        }

        return data.toString();
    }

    /**
     * Load from file collection.
     *
     * @param fileName Value of file name
     * @return Array of organizations
     */
    public Organization[] load(String fileName) {
        try {
            File file = new File(fileName + ".csv");
            Scanner scanner = new Scanner(file);

            LinkedList<Organization> organizations = new LinkedList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                Organization organization = getOrganization(parts);
                organizations.add(organization);
            }
            scanner.close();

            return organizations.toArray(new Organization[0]);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return new Organization[0];
    }

    /**
     * Building organization from parts of csv line.
     *
     * @param parts Array of string parts
     * @return Organization from csv line
     */
    private Organization getOrganization(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        Coordinates coordinates = new Coordinates(Double.parseDouble(parts[2]), Float.parseFloat(parts[3]));
        ZonedDateTime creationDate = ZonedDateTime.parse(parts[4]);
        long annualTurnover = Long.parseLong(parts[5]);
        String fullName = parts[6];
        int employeesCount = Integer.parseInt(parts[7]);

        OrganizationType type = null;
        for (OrganizationType organizationType : OrganizationType.values()) {
            if (organizationType.name().equals(parts[8])) {
                type = organizationType;
                break;
            }
        }
        if (type == null) {
            throw new IllegalArgumentException("Invalid organization type: " + parts[8]);
        }

        Location location =  new Location(Double.parseDouble(parts[10]),
                Long.parseLong(parts[11]),
                Float.parseFloat(parts[12]));
        Address postalAddress = new Address(parts[9], location);

        return new Organization(id, name, coordinates, creationDate, annualTurnover,
                fullName, employeesCount, type, postalAddress);
    }
}
