package Items.Abstactions;

public abstract class Item {
    private final String name;

    public Item(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object){
        if (this.getClass() != object.getClass()) return false;

        return this.name.equals(((Item) object).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
