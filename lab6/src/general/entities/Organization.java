package general.entities;

import java.io.Serializable;
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
public class Organization implements Comparable<Organization>, Serializable {
    private static int countUniqueId;

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private int employeesCount; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null

    public Organization() {}

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

        setId(id);
        setMaxId(id);
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(creationDate);
        setAnnualTurnover(annualTurnover);
        setFullName(fullName);
        setEmployeesCount(employeesCount);
        setType(type);
        setPostalAddress(postalAddress);
    }

    /**
     * Method which counting all created organizations.
     *
     * @return id that greater than others.
     */
    public static int getUniqueId() {
        return ++countUniqueId;
    }

    public void setUniqueId() {
        setId(getUniqueId());
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
     * Getter of id
     *
     * @return Value of id field
     */
    public int getId() {
        return id;
    }

    /**
     * Setter of id
     *
     * @param id Value of id field
     */
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException();
        this.id = id;
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
     * Setter of name
     *
     * @param name Value of name field
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        this.name = name;
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
     * Setter of coordinates
     *
     * @param coordinates  Value of coordinates field
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException();
        this.coordinates = coordinates;
    }

    /**
     * Getter of creationDate
     *
     * @return Value of creationDate field
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Setter of creationDate
     *
     * @param creationDate Value of creationDate field
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate == null) throw new IllegalArgumentException();
        this.creationDate = creationDate;
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
     * Setter of annualTurnover
     *
     * @param annualTurnover Value of annualTurnover field
     */
    public void setAnnualTurnover(Long annualTurnover) {
        if (annualTurnover == null || annualTurnover < 0) throw new IllegalArgumentException();
        this.annualTurnover = annualTurnover;
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
     * Setter of fullName
     *
     * @param fullName Value of fullName field
     */
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) throw new IllegalArgumentException();
        this.fullName = fullName;
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
     * Setter of employeesCount
     *
     * @param employeesCount Value of employeesCount field
     */
    public void setEmployeesCount(int employeesCount) {
        if (employeesCount < 0) throw new IllegalArgumentException();
        this.employeesCount = employeesCount;
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
     * Setter of type
     *
     * @param type  Value of type field
     */
    public void setType(OrganizationType type) {
        if (type == null) throw new IllegalArgumentException();
        this.type = type;
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
     * Setter of postalAddress
     *
     * @param postalAddress Value of postalAddress field
     */
    public void setPostalAddress(Address postalAddress) {
        if (postalAddress == null) throw new IllegalArgumentException();
        this.postalAddress = postalAddress;
    }

    /**
     * Copy all field from param organization without id and creationDate
     *
     * @param organization Organization from which coping fields
     */
    public void copy(Organization organization) {
        setName(organization.getName());
        setCoordinates(organization.getCoordinates());
        setCreationDate(organization.getCreationDate());
        setAnnualTurnover(organization.getAnnualTurnover());
        setFullName(organization.getFullName());
        setEmployeesCount(organization.getEmployeesCount());
        setType(organization.getType());
        setPostalAddress(organization.getPostalAddress());
    }

    /**
     * Validate field
     *
     * @return True if exist an error
     */
    public boolean valid() {
        return id < 0 || name == null || name.isEmpty() || coordinates == null || creationDate == null
                || annualTurnover == null || annualTurnover < 0
                || fullName == null || fullName.isEmpty() || employeesCount < 0
                || type == null || postalAddress == null;
    }

    @Override
    public int compareTo(Organization o) {
        return fullName.compareTo(o.fullName);
    }

    @Override
    public String toString() {
        return String.format("Id=%d;\t Name=%s;\t Coordinates=%s;\t Time=%s;\t Annual turnover=%d;" +
                "\t Full name - %s,\t Employees count=%d;\t Type=%s;\t PostalAddress=%s",
                id, name, coordinates, creationDate, annualTurnover, fullName, employeesCount, type, postalAddress);
    }
}

