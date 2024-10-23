package Actions;

import Actions.Abstractions.ActionHumans;
import Entities.Human;

public class PeaceAction extends ActionHumans {
    public PeaceAction(Human human1, Human human2){
        super(human1, human2);
    }

    @Override
    public boolean start() {
        System.out.println(human1 + " и " + human2 + " помирились");
        return true;
    }
}
