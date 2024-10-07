package src.attacks;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    public void applySelfEffects(Pokemon p) {
        p.setMod(Stat.EVASION, 1);
    }

    @Override
    public String describe() {
        return "применяет Double Team";
    }
}
