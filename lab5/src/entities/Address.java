package entities;


/**
 * Entity of address. Contains getters of each field.
 * Restrictions on fields: zipCode cannot be null, town cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Address {
    private String zipCode; //Поле не может быть null
    private Location town; //Поле может быть null

    public Address() {}

    /** Standard constructor. Check validate fields.
     *
     * @param zipCode Value of field zipCode
     * @param town Value of field town
     */
    public Address(String zipCode, Location town) {
        this.zipCode = zipCode;
        this.town = town;
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
     * Setter of zip code
     *
     * @param zipCode Value of zip code
     */
    public void setZipCode(String zipCode) {
        if (zipCode == null) throw new IllegalArgumentException();
        this.zipCode = zipCode;
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
     * Setter of town
     *
     * @param town Value of town
     */
    public void setTown(Location town) {
        if (town == null) throw new IllegalArgumentException();
        this.town = town;
    }

    @Override
    public String toString() {
        return String.format("(Zip code=%s;Town=%s)", zipCode, town);
    }
}