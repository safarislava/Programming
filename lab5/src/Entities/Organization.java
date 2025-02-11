package Entities;

import java.time.ZonedDateTime;

/**
 * Entity of organization. Contains getter of each field.
 * Restrictions on fields:
 * id must be greater than 0, must be unique, set automatic,
 * name cannot be null or empty,
 * coordinates cannot be null,
 * creationDate cannot be null, set automatic,
 * annualTurnover cannot be null, must be greater than 0,
 * fullName cannot be null,
 * employeesCount must be greater than 0,
 * organizationType cannot be null,
 * postalAddress cannot be null
 *
 * @since 1.0
 * @author safarislava
 */
public class Organization implements Comparable<Organization> {
    private static int countUniqueId;

    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null

    /**
     * Constructor. Set all field. Id and creationDate set automatic.
     *
     * @param name Value of name field
     * @param coordinates Value of coordinates field
     * @param annualTurnover Value of annualTurnover field
     * @param fullName Value of fullName field
     * @param employeesCount Value of employeesCount field
     * @param type Value of type field
     * @param postalAddress Value of postalAddress field
     */
    public Organization(String name, Coordinates coordinates, long annualTurnover, String fullName,
                        int employeesCount, OrganizationType type, Address postalAddress) {

        this(getUniqueId(), name, coordinates, ZonedDateTime.now(), annualTurnover, fullName,
                employeesCount, type, postalAddress);
    }

    /**
     * Constructor. Set all field.
     *
     * @param id Value of id field
     * @param name Value of name field
     * @param coordinates Value of coordinates field
     * @param creationDate Value of creationDate field
     * @param annualTurnover Value of annualTurnover field
     * @param fullName Value of fullName field
     * @param employeesCount Value of employeesCount field
     * @param type Value of type field
     * @param postalAddress Value of postalAddress field
     */
    public Organization(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, long annualTurnover,
                        String fullName, int employeesCount, OrganizationType type, Address postalAddress) {

        this.id = id;
        setMaxId(id);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;

        if (valid()) throw new RuntimeException("Invalid organization");
    }

    /**
     * Method which counting all created organizations.
     *
     * @return id that greater than others.
     */
    private static int getUniqueId() {
        return ++countUniqueId;
    }

    /**
     * Method which counting max id
     *
     * @param id Value of id organization
     */
    private static void setMaxId(int id){
        countUniqueId = Math.max(countUniqueId, id);
    }

    /**
     * Validate field.
     *
     * @return True if all fields are valid
     */
    public boolean valid() {
        boolean valid = id > 0 &&
                name != null && !name.isEmpty() &&
                coordinates != null &&
                creationDate != null &&
                annualTurnover > 0 &&
                employeesCount > 0 &&
                type != null &&
                postalAddress != null;

        return !valid;
    }

    /**
     * Getter of id
     *
     * @return Value of id field
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of name
     *
     * @return Value of name field
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of coordinates
     *
     * @return Value of coordinates field
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Getter of creationDate
     *
     * @return Value of creationDate field
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Getter of annualTurnover
     *
     * @return Value of annualTurnover field
     */
    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Getter of fullName
     *
     * @return Value of fullName field
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Getter of employeesCount
     *
     * @return Value of employeesCount field
     */
    public int getEmployeesCount() {
        return employeesCount;
    }

    /**
     * Getter of type
     *
     * @return Value of type field
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Getter of postalAddress
     *
     * @return Value of postalAddress field
     */
    public Address getPostalAddress() {
        return postalAddress;
    }

    /**
     * Copy all field from param organization without id and creationDate
     *
     * @param organization Organization from which coping fields
     */
    public void copy(Organization organization) {
        if (organization.valid()) throw new RuntimeException("Invalid organization");

        name = organization.name;
        coordinates = organization.coordinates;
        annualTurnover = organization.annualTurnover;
        fullName = organization.fullName;
        employeesCount = organization.employeesCount;
        type = organization.type;
        postalAddress = organization.postalAddress;
    }

    @Override
    public int compareTo(Organization o) {
        return (id - o.getId()) + name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format("Id - %d,\t Name - %s,\t Time - %s,\t Employees count - %d,\t Type - %s",
                id, name, creationDate, employeesCount, type);
    }
}

