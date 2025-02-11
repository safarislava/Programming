package Entities;

/**
 * Entity of location. Contains getter of each field.
 * Restrictions on fields: y cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Location {
    private final double x;
    private final Long y; //Поле не может быть null
    private final float z;

    /**
     * Standard constructor. Validate fields.
     *
     * @param x Value of x field
     * @param y Value of y field
     * @param z Value of z field
     */
    public Location(double x, Long y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        if (!valid()) throw new RuntimeException("Invalid location");
    }

    /**
     * Getter of x
     *
     * @return Value of x field
     */
    public double getX() {
        return x;
    }

    /**
     * Getter of y
     *
     * @return Value of y field
     */
    public Long getY() {
        return y;
    }

    /**
     * Getter of z
     *
     * @return Value of z field
     */
    public float getZ() {
        return z;
    }

    /**
     * Validate field.
     *
     * @return True if all fields are valid
     */
    public boolean valid(){
        return y != null;
    }
}
