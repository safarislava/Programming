package src.pokemons;

import src.attacks.*;

public class Marowak extends Cubone {
    public Marowak(String name, int level) {
        super(name, level);
        addMove(new FocusBlast());
    }
}
