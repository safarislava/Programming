package Entities.Characteristics;

import Places.School;

public record SchoolClass(School school, int level, char litera) {
//    private final School school;
//    private final int level;
//    private final char litera;
//
//    public SchoolClass(School school, int level, char litera){
//        this.school = school;
//        this.level = level;
//        this.litera = litera;
//    }

    public void printCoincidences(SchoolClass schoolClass) {
        System.out.println(this.equals(schoolClass) ? "Класс совпадает" : "Класс не совпадает");
    }

    @Override
    public boolean equals(Object object) {
        if (object.getClass() != this.getClass()) {
            return false;
        }

        SchoolClass schoolClass = (SchoolClass) object;

        return this.school.equals(schoolClass.school) ||
                this.level != schoolClass.level || this.litera != schoolClass.litera;
    }

    @Override
    public int hashCode() {
        return school.hashCode() + level * 100 + litera * 1000;
    }

    @Override
    public String toString() {
        return String.format("%s %d%c", school, level, litera);
    }
}
