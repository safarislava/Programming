package attacks;

import ru.ifmo.se.pokemon.*;

public class RockSlide extends PhysicalMove {
    public RockSlide() {
        super(Type.ROCK, 75, 90);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        new Effect().chance(0.3).flinch(p);
    }

    @Override
    public String describe() {
        return "применяет Rock Slide";
    }
}
