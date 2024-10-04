package src.pokemons;

import src.attacks.*;
import ru.ifmo.se.pokemon.*;

public class Victreebel extends Pokemon{
    public Victreebel(String name, int level) {
        super(name, level);
        setStats(80, 105, 65, 100, 70, 70);
        setType(Type.GRASS, Type.POISON);
        setMove(new Swagger(), new RazorLeaf(), new Growth(), new SludgeBomb());
    }
}