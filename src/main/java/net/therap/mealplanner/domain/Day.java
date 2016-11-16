package net.therap.mealplanner.domain;

/**
 * @author bashir
 * @since 11/16/16
 */
public enum Day {

    SUN("SUN"), MON("MON"), TUE("TUE"), WED("WED"), THU("THU"), FRI("FRI"), SAT("SAT");

    private String day;

    Day(String day) {
        this.day = day;
    }
}
