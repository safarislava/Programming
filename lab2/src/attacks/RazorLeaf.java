package src.attacks;

import ru.ifmo.se.pokemon.*;

public class RazorLeaf extends PhysicalMove {
    public RazorLeaf() {
        super(Type.GRASS, 55, 95);
    }

    @Override
    protected double calcCriticalHit(Pokemon var1, Pokemon var2) {
        if (var1.getStat(Stat.SPEED) / 512.0 * 3 > Math.random()) {
            return 2.0;
        } else {
            return 1.0;
        }
     }

    @Override
    public String describe() {
        return "применяет RazorLeaf";
    }
}