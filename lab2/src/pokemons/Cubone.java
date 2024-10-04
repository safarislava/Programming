package src.pokemons;

import src.attacks.*;
import ru.ifmo.se.pokemon.*;

public class Cubone extends Pokemon{
    public Cubone(String name, int level) {
        super(name, level);
        setStats(50, 50, 95, 40, 50, 35);
        setType(Type.GROUND);
        setMove(new Leer(), new Confide(), new DoubleTeam());
    }
}
