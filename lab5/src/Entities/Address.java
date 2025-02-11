package Entities;


/**
 * Entity of address. Contains getters of each field.
 * Restrictions on fields: zipCode cannot be null, town cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Address {
    private final String zipCode; //Поле не может быть null
    private final Location town; //Поле может быть null

    /** Standard constructor. Check validate fields.
     *
     * @param zipCode Value of field zipCode
     * @param town Value of field town
     */
    public Address(String zipCode, Location town) {
        this.zipCode = zipCode;
        this.town = town;

        if (!valid()) throw new RuntimeException("Invalid address");
    }

    /**
     * Getter of zipCode
     *
     * @return Value of zipCode field
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Getter of town
     *
     * @return Value of town field
     */
    public Location getTown() {
        return town;
    }

    /**
     * Validate fields
     *
     * @return True if all fields are valid
     */
    public boolean valid() {
        return zipCode != null && town != null;
    }
}