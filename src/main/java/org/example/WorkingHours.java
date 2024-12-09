package org.example;

public class WorkingHours {
    private int startHour;
    private int endHour;

    public WorkingHours(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}

