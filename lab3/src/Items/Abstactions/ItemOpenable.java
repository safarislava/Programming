package Items.Abstactions;

import Items.Interfaces.Openable;

public abstract class ItemOpenable extends Item implements Openable {
    private boolean isOpen = false;

    public ItemOpenable(String name) {
        super(name);
    }

    @Override
    public void open() {
        isOpen = !isOpen;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }
}
