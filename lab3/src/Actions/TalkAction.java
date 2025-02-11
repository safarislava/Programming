package Actions;

import Entities.Human;
import Exceptions.NullOpinionException;

public class TalkAction {
    private final Human human1;
    private final Human human2;
    private String statement;

    public TalkAction(Human human1, Human human2) {
        this.human1 = human1;
        this.human2 = human2;
    }

    public void setStatement(String statement){
        this.statement = statement;
    }

    public void setOpinion(Object object) throws NullOpinionException {
        if (human1.getOpinion(object) == null) {
            throw new NullOpinionException(String.format("Малыш ничего не думает о %s", object));
        }

        setStatement(String.format("%s %s", object, human1.getOpinion(object)));
    }

    public boolean start() {
        System.out.printf("%s рассказывает %s, что %s%n", human1, human2, statement);

        if (!human2.isTrust(human1)) {
            System.out.printf("%s не поверил%n", human2);
            return false; // disagree
        }
        return true;
    }
}
