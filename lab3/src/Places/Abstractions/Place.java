package Places.Abstractions;

import Items.Interfaces.Openable;

import java.util.ArrayList;

abstract public class Place {
    private final String title;
    private final ArrayList<Openable> openables;

    public Place(String title){
        this.title = title;
        openables = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addOpenable(Openable item) {
        openables.add(item);
    }

    public ArrayList<Openable> getOpenables() {
        return openables;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object object) {
        if (this.getClass() != object.getClass()) return false;

        return this.title.equals(((Place) object).title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
