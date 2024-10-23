package Actions.Interfaces;

import Entities.Human;

public abstract class ActionHumans {
    protected Human human1;
    protected Human human2;

    public ActionHumans(Human human1, Human human2){
        this.human1 = human1;
        this.human2 = human2;
    }

    public abstract boolean start();
}
