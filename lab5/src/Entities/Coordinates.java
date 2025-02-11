package Entities;


/**
 * Entity of coordinates. Contains getters of each field.
 * Restrictions on fields: y cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Coordinates {
    private final double x;
    private final Float y; //Поле не может быть null

    /**
     * Standard constructor. Validate fields.
     *
     * @param x Value of x field
     * @param y Value of y field
     */
    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;

        if (!valid()) throw new RuntimeException("Invalid coordinates");
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
    public Float getY() {
        return y;
    }

    /**
     * Validate fields
     *
     * @return True if all fields are valid
     */
    public boolean valid() {
        return y != null;
    }
}