package Items.Abstactions;

public abstract class Weapon extends Item {
    private int powerScale;
    private int durability;

    public Weapon(String name, int powerScale, int durability) {
        super(name);
        this.powerScale = powerScale;
    }

    @Override
    public boolean use() {
        if (durability == 0) return false;
        durability--;
        return true;
    }

    public int getPowerScale() {
        return powerScale;
    }
}
