package Places;

import Places.Abstractions.Place;

public class Street extends Place {
    public Street(String name) {
        super("Улица " + name);
    }
}
