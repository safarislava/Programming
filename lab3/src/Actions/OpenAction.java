package Actions;

import Entities.Human;
import Items.Interfaces.Openable;

public class OpenAction {
    protected final Human human;
    protected final Openable item;

    public OpenAction(Human human, Openable item) {
        this.human = human;
        this.item = item;
    }

    public boolean start() {
        if (item == null){
            return false;
        }

        item.open();

        System.out.printf("%s %s %s%n", human, item.isOpen() ? "открыл" : "закрыл", item);

        return true;
    }
}
