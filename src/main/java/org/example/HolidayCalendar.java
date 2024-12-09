package org.example;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidayCalendar {
    private Set<LocalDate> holidays;

    public HolidayCalendar() {
        holidays = new HashSet<>();
        holidays.add(LocalDate.of(2024, 1, 1));  
        holidays.add(LocalDate.of(2024, 12, 25));  
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
