package Entities.Characteristics;

import Places.Street;

public record Address(Street street, int house, int flat) {
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        Address address = (Address) object;
        if (this.street != address.street) return false;

        if (this.house != address.house) {
            System.out.println("Адреса совпадают вполоть до улицы");
            return true;
        }

        if (this.flat != address.flat) {
            System.out.println("Адреса совпадают вполоть до дома");
            return true;
        }

        System.out.println("Адреса совпадают вполоть до квартиры");
        return true;
    }
}
