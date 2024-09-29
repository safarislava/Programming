package pokemons;

import attacks.*;
import ru.ifmo.se.pokemon.*;

public class Weepinbell extends Pokemon{
    public Weepinbell(String name, int level) {
        super(name, level);
        setStats(65, 90, 50, 85, 45, 55);
        setType(Type.GRASS, Type.POISON);
        setMove(new Swagger(), new RazorLeaf(), new Growth());
    }
}