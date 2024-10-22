package Actions;

import Entities.Human;
import Items.Abstactions.ItemOpenable;

public class OpenAction {
    protected Human human;
    protected ItemOpenable item;

    public OpenAction(Human human, ItemOpenable item) {
        this.human = human;
        this.item = item;
    }

    public boolean start() {
        item.use();

        System.out.print(human.getName());
        if (item.isOpen()){
            System.out.print(" открыл ");
        }
        else {
            System.out.print(" закрыл ");
        }
        System.out.println(item.getName());

        return true;
    }
}
