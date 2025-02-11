package Items;

import Items.Abstactions.Item;
import Items.Interfaces.Openable;

public class Wardrobe extends Item implements Openable {
    private boolean isOpen = false;

    public Wardrobe() {
        super("Шкаф");
    }

    public void open() {
        isOpen = !isOpen;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }
}
