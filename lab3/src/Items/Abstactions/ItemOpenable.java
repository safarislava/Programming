package Items.Abstactions;

public abstract class ItemOpenable extends Item {
    private boolean isOpen = false;

    public ItemOpenable(String name) {
        super(name);
    }

    @Override
    public boolean use() {
        isOpen = !isOpen;
        return true;
    }

    public boolean isOpen(){
        return isOpen;
    }
}
