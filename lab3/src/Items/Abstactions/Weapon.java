package Items.Abstactions;

import Items.Interfaces.Usable;

public abstract class Weapon extends Item implements Usable {
    private final int powerScale;
    private int durability;

    public Weapon(String name, int powerScale, int durability) {
        super(name);
        this.powerScale = powerScale;
        this.durability = durability;
    }

    @Override
    public boolean use() {
        if (durability == 0) {
            System.out.printf("%s сломано%n", this);
            return false;
        }
        durability--;
        return true;
    }

    public int getPowerScale() {
        return powerScale;
    }
}
