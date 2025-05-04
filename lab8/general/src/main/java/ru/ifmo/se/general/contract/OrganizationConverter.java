package ru.ifmo.se.general.contract;

import ru.ifmo.se.general.entity.*;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class OrganizationConverter {
    private static Organization decodePiece(String text) {
        String[] fields = text.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        Coordinates coordinates = new Coordinates(Double.parseDouble(fields[2]), Float.parseFloat(fields[3]));
        ZonedDateTime creationDate = ZonedDateTime.parse(fields[4]);
        long annualTurnover = Long.parseLong(fields[5]);
        String fullName = fields[6];
        int employeesCount = Integer.parseInt(fields[7]);
        OrganizationType type = OrganizationType.valueOf(fields[8]);
        Location location = new Location(Double.parseDouble(fields[10]), Long.parseLong(fields[11]), Float.parseFloat(fields[12]));
        Address postalAddress  = new Address(fields[9], location);

        return new Organization(id, name, coordinates, creationDate, annualTurnover, fullName, employeesCount, type, postalAddress);
    }

    private static OrganizationDto decodeDtoPiece(String text) {
        String[] fields = text.split(",");

        Organization organization = decodePiece(text);
        String creator = fields[13];

        return new OrganizationDto(organization, creator);
    }

    public static List<Organization> decode(String text) {
        List<Organization> organizations = new LinkedList<>();
        if (text.equals(CodePhrase.SKIP)) return organizations;

        String[] organizationsText = text.split(";");

        for (String organization : organizationsText) {
            organizations.add(decodePiece(organization));
        }

        return organizations;
    }

    public static List<OrganizationDto> decodeDto(String text) {
        List<OrganizationDto> organizations = new LinkedList<>();
        if (text.equals(CodePhrase.SKIP)) return organizations;

        String[] organizationsText = text.split(";");

        for (String organization : organizationsText) {
            organizations.add(decodeDtoPiece(organization));
        }

        return organizations;
    }

    private static StringBuilder encodePiece(Organization organization) {
        StringBuilder text = new StringBuilder();
        text.append(organization.getId()).append(",");
        text.append(organization.getName()).append(",");
        text.append(organization.getCoordinates().getX()).append(",");
        text.append(organization.getCoordinates().getY()).append(",");
        text.append(organization.getCreationDate()).append(",");
        text.append(organization.getAnnualTurnover()).append(",");
        text.append(organization.getFullName()).append(",");
        text.append(organization.getEmployeesCount()).append(",");
        text.append(organization.getType()).append(",");
        text.append(organization.getPostalAddress().getZipCode()).append(",");
        text.append(organization.getPostalAddress().getTown().getX()).append(",");
        text.append(organization.getPostalAddress().getTown().getY()).append(",");
        text.append(organization.getPostalAddress().getTown().getZ());
        return text;
    }

    public static String encode(Organization organization) {
        return encodePiece(organization).append(";").toString();
    }

    public static String encode(OrganizationDto organizationDto) {
        return encodePiece(organizationDto.organization).append(",").append(organizationDto.creator).append(";").toString();
    }
}
