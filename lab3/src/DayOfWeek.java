public enum DayOfWeek {
    MONDAY ("Понедельник"),
    TUESDAY ("Вторник"),
    WEDNESDAY ("Среда"),
    THURSDAY ("Четверг"),
    FRIDAY ("Пятница"),
    SATURDAY ("Суббота"),
    SUNDAY ("Воскресенье");

    private final String title;

    DayOfWeek(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}