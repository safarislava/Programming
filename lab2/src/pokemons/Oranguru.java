package pokemons;

import attacks.*;
import ru.ifmo.se.pokemon.*;

public class Oranguru extends Pokemon {
    public Oranguru(String name, int level){
        super(name, level);
        setStats(90, 60, 80, 90, 110, 60);
        setType(Type.NORMAL, Type.PSYCHIC);
        setMove(new RockSlide(), new ShadowBall(), new DreamEater(), new EnergyBall());
    }
}
