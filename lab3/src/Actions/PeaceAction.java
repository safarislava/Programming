package Actions;

import Entities.Human;

public class PeaceAction {
    private final Human human1;
    private final Human human2;

    public PeaceAction(Human human1, Human human2){
        this.human1 = human1;
        this.human2 = human2;
    }

    public boolean start() {
        System.out.printf("%s и %s помирились%n", human1, human2);
        human1.addTrust(human2, Human.FRIEND_TRUST);
        human2.addTrust(human1, Human.FRIEND_TRUST);
        return true;
    }
}
