package org.example;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SellerAvailability {
    private String name;
    private Map<DayOfWeek, WorkingHours> workingHoursByDay;  
    private int averageDeliveryDays;
    private ZoneId timeZone;

    public SellerAvailability(String name, Map<DayOfWeek, WorkingHours> workingHoursByDay, int averageDeliveryDays, ZoneId timeZone) {
        this.name = name;
        this.workingHoursByDay = workingHoursByDay;
        this.averageDeliveryDays = averageDeliveryDays;
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public boolean isWorkingDayAndHour(ZonedDateTime dateTime, boolean isOrderDay) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        WorkingHours workingHours = workingHoursByDay.get(dayOfWeek);

        if (workingHours == null) {
            return false;
        }

        if (isOrderDay) {
            int hour = dateTime.getHour();
            int minute = dateTime.getMinute();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            System.out.print(dateTime.format(formatter) + " ");

            boolean isAfterWorkingHoursStart = (hour > workingHours.getStartHour()) ||
                    (hour == workingHours.getStartHour() && minute >= 0);

            boolean isBeforeWorkingHoursEnd = (hour < workingHours.getEndHour()) ||
                    (hour == workingHours.getEndHour() && minute == 0);

            return isAfterWorkingHoursStart && isBeforeWorkingHoursEnd;
        }

        return true;
    }


    public int getStartHour(DayOfWeek dayOfWeek) {
        WorkingHours workingHours = workingHoursByDay.get(dayOfWeek);
        return workingHours != null ? workingHours.getStartHour() : 0;
    }

    public int getAverageDeliveryDays() {
        return averageDeliveryDays;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }
}

