import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle battle = new Battle();

        Pokemon oranguru = new Oranguru("Leonardo", 1);
        Pokemon cubone = new Cubone("Raf", 1);
        Pokemon marowak = new Marowak("Donatello", 1);
        battle.addAlly(oranguru);
        battle.addAlly(cubone);
        battle.addAlly(marowak);

        Pokemon bellsprout = new Bellsprout("Micky", 1);
        Pokemon weepinbell = new Weepinbell("Splinter", 1);
        Pokemon victreebel = new Victreebel("Shredder", 1);
        battle.addFoe(bellsprout);
        battle.addFoe(weepinbell);
        battle.addFoe(victreebel);

        battle.go();
    }
}
