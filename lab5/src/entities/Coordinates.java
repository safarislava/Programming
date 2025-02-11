package entities;


/**
 * Entity of coordinates. Contains getters of each field.
 * Restrictions on fields: y cannot be null.
 *
 * @since 1.0
 * @author safarislava
 */
public class Coordinates {
    private double x;
    private Float y; //Поле не может быть null

    public Coordinates() {}

    /**
     * Standard constructor. Validate fields.
     *
     * @param x Value of x field
     * @param y Value of y field
     */
    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;
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
    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        if (y == null) throw new IllegalArgumentException();
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f;%.2f)", x, y);
    }
}