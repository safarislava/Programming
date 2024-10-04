package src.attacks;

import ru.ifmo.se.pokemon.*;

public class Growth extends SpecialMove {
    public Growth() {
        super(Type.NORMAL, 0, 0);
    }
    
    @Override
    public void applySelfEffects(Pokemon p) {
        p.setMod(Stat.ATTACK, 1);
        p.setMod(Stat.SPECIAL_ATTACK, 1);
    }

    @Override
    public String describe() {
        return "применяет Growth";
    }
} 
