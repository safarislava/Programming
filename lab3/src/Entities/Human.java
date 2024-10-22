package Entities;

import Entities.Characteristics.Address;
import Places.Place;
import Places.Street;

import java.util.HashMap;

public class Human {
    protected String name;
    protected Address address;
    protected Place place;

    private HashMap<Object, String> opinions;
    private HashMap<Human, Float> trust;

    public Human(String name, Address address) {
        this.name = name;
        this.address = address;
        this.opinions = new HashMap<Object, String>();

        trust = new HashMap<>();
    }

    public void addOpinion(Human human, String level) {
        opinions.put(human, level);
        System.out.println(name + " относится к " + human.getName() + " так: " + level);
    }

    public String getOpinion(Object object) {
        return opinions.get(object);
    }

    public void setTrust(Human human, float level) {
        trust.put(human, level);
    }

    public boolean isTrust(Human human) {
        return Math.random() < trust.get(human);
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Place getPlace() {
        return place;
    }

    public void enter(Place place) {
        this.place = place;
        System.out.println(name + " зашёл в " + place.getTitle());
    }
}
