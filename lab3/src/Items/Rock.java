package Items;

import Items.Abstactions.Weapon;

public class Rock extends Weapon {
    public Rock() {
        super("Камень", 5, 1);
    }

    public Rock(int weight, int durability) {
        super("Камень", weight, durability);
    }
}
