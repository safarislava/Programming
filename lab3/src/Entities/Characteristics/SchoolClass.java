package Entities.Characteristics;

import Places.School;

public record SchoolClass(School school, int level, int litera) {
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        SchoolClass schoolClass = (SchoolClass) object;
        if (!this.school.equals(schoolClass.school)) return false;
        if (this.level != schoolClass.level || this.litera != schoolClass.litera) {
            System.out.println("Совпадает только школа");
            return true;
        }

        System.out.println("Совпадает класс");
        return true;
    }
}
