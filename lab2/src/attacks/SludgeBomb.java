package attacks;

import ru.ifmo.se.pokemon.*;

public class SludgeBomb extends SpecialMove {
    public SludgeBomb() {
        super(Type.POISON, 90, 100);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        new Effect().chance(0.3).poison(p);
    }

    @Override
    public String describe() {
        return "применяет Sludge Bomb";
    }
}
