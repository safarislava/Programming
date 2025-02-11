package Entities.Characteristics;

import Places.Street;

public record Address(Street street, int house, int flat) {
//    private final Street street;
//    private final int house;
//    private final int flat;
//
//    public Address(Street street, int house, int flat){
//        this.street = street;
//        this.house = house;
//        this.flat = flat;
//    }

    public void printCoincidences(Address address) {
        if (!this.equals(address)){
            System.out.println("Адреса не совпадают");
            return;
        }

        if (this.house != address.house) {
            System.out.println("Адреса совпадают вплоть до улицы");
            return;
        }

        if (this.flat != address.flat) {
            System.out.println("Адреса совпадают вплоть до дома");
            return;
        }

        System.out.println("Адреса совпадают вплоть до квартиры");
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        Address address = (Address) object;

        return this.street == address.street;
    }

    @Override
    public int hashCode() {
        return street.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Улица %s, дом %s, квартира %s", street, house, flat);
    }
}
