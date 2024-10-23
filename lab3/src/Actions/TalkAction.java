package Actions;

import Actions.Interfaces.ActionHumans;
import Entities.Human;
import Exceptions.NullOpinionException;

public class TalkAction extends ActionHumans {
    private String statement;

    public TalkAction(Human human1, Human human2) {
        super(human1, human2);
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    public void setOpinion(Object object) {
        if (human1.getOpinion(object) == null) {
            throw new NullOpinionException("Малыш ничего не думает о " + object);
        }

        setStatement(object + " " + human1.getOpinion(object));
    }

    @Override
    public boolean start() {
        System.out.println(human1+ " рассказывает " + human2 + ", что " + statement);

        if (!human2.isTrust(human1)) {
            System.out.println(human2 + " неповерил");
            return false; // disagree
        }
        return true;
    }
}
