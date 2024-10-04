package src.attacks;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {
    public DreamEater() {
        super(Type.PSYCHIC, 100, 100);
    }

    @Override
    protected boolean checkAccuracy(Pokemon var1, Pokemon var2) {
        return super.checkAccuracy(var1, var2) && (var2.getCondition() == Status.SLEEP);
    }

    @Override
    public void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, 1);
    }

    @Override
    public String describe() {
        return "применяет Dream Eater";
    }
}