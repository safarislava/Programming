package Items.Abstactions;

import Items.Interfaces.Usable;

public abstract class Weapon extends Item implements Usable {
    private int powerScale;
    private int durability;

    public Weapon(String name, int powerScale, int durability) {
        super(name);
        this.powerScale = powerScale;
        this.durability = durability;
    }

    public boolean use() {
        if (durability == 0) return false;
        durability--;
        return true;
    }

    public int getPowerScale() {
        return powerScale;
    }
}
