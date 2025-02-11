package Entities;

import Entities.Characteristics.Address;
import Places.Abstractions.Place;

import java.util.HashMap;

public class Human {
    public static final float MAX_TRUST = 1.0f;
    public static final float MIN_TRUST = 0.0f;
    public static final float DEFAULT_TRUST = 0.0f;
    public static final float FRIEND_TRUST = 0.9f;

    protected final String name;
    protected final Address address;
    protected Place place;

    private final HashMap<Object, String> opinions;
    private final HashMap<Human, Float> trust;

    public Human(String name, Address address) {
        this.name = name;
        this.address = address;
        this.opinions = new HashMap<>();
        this.trust = new HashMap<>();
    }

    public void addOpinion(Human human, String level) {
        opinions.put(human, level);
        System.out.printf("%s относится к %s так: %s%n", name, human, level);
    }

    public String getOpinion(Object object) {
        return opinions.get(object);
    }

    public boolean isFriend(Human human) {
        if (trust.get(human) == null) return false;
        return trust.get(human) > FRIEND_TRUST;
    }

    public void setTrust(Human human, float level) {
        trust.put(human, Math.min(Math.max(level, MIN_TRUST), MAX_TRUST));
    }

    public void addTrust(Human human, float level){
        trust.putIfAbsent(human, DEFAULT_TRUST);

        float newTrust = trust.get(human) + level;

        setTrust(human, newTrust);
    }

    public boolean isTrust(Human human) {
        return Math.random() < trust.get(human);
    }

    public Address getAddress() {
        return address;
    }

    public Place getPlace() {
        return place;
    }

    public void enter(Place place) {
        this.place = place;
        System.out.printf("%s зашёл в %s%n", name, place.getTitle());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object){
        if (this.getClass() != object.getClass()) return false;

        return this.name.equals(((Human) object).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
