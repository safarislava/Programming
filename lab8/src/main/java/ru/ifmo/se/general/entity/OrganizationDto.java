package ru.ifmo.se.general.entity;

public class OrganizationDto {
    public Organization organization;
    public String creator;

    public OrganizationDto(Organization organization, String creator) {
        this.organization = organization;
        this.creator = creator;
    }
}
