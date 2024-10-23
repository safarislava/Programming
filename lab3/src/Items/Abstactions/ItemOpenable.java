package Items.Abstactions;

import Items.Interfaces.Openable;

public abstract class ItemOpenable extends Item implements Openable {
    private boolean isOpen = false;

    public ItemOpenable(String name) {
        super(name);
    }

    public void open() {
        isOpen = !isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
