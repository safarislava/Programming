package Places;

import Items.Abstactions.Item;

import java.util.ArrayList;

public class Place {
    private String title;
    private ArrayList<Item> items;

    public Place(String title){
        this.title = title;
        items = new ArrayList<Item>();
    }

    public String getTitle() {
        return title;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public <T extends Item> ArrayList<T> getItemsByType() {
        ArrayList<T> itemsSearched = new ArrayList<>();

        for (Item item : items) {
            try {
                // TODO unchecked exception
                itemsSearched.add((T) item);
            } catch (ClassCastException ignored) {}
        }

        return itemsSearched;
    }
}