package entities;

/**
 * Entity of location. Contains getter of each field.
 * Restrictions on fields: y cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Location {
    private double x;
    private Long y; //Поле не может быть null
    private float z;

    public Location() {}

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
    }

    /**
     * Getter of x
     *
     * @return Value of x field
     */
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter of y
     *
     * @return Value of y field
     */
    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        if (y == null) throw new IllegalArgumentException();
        this.y = y;
    }

    /**
     * Getter of z
     *
     * @return Value of z field
     */
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("(%.2f;%d;%.2f)", x, y, z);
    }
}
