package Actions;

import Actions.Abstractions.ActionHumans;
import Entities.Human;

public class TalkAction extends ActionHumans {
    private String statement;

    public TalkAction(Human human1, Human human2) {
        super(human1, human2);
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    @Override
    public boolean start() {
        System.out.println(human1.getName() + " рассказывает " + human2.getName() + ", что " + statement);

        // TODO trust
        if (!human2.isTrust(human1)) {
            System.out.println(human2.getName() + " неповерил");
            return false; // disagree
        }
        return true;
    }
}
