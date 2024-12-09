package org.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryScheduler {
    private static DeliveryScheduler instance;
    private final List<SellerAvailability> sellers;
    private HolidayCalendar holidayCalendar;

    private DeliveryScheduler() {
        sellers = new ArrayList<>();
        holidayCalendar = new HolidayCalendar();
    }

    public static DeliveryScheduler getInstance() {
        if (instance == null) {
            instance = new DeliveryScheduler();
        }
        return instance;
    }

    public DeliveryScheduler(List<SellerAvailability> sellers) {
        this.sellers = sellers;
    }

    public void addSeller(SellerAvailability seller) {
        sellers.add(seller);
    }

    public ZonedDateTime calculateDeliveryDate(LocalDateTime orderDate, String sellerName, DeliveryMethod deliveryMethod, double distance) {
        ZoneId myTimeZone = ZoneId.systemDefault();

        SellerAvailability seller = sellers.stream()
            .filter(s -> s.getName().equals(sellerName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Продавця не знайдено"));

        ZonedDateTime estimatedDate = ZonedDateTime.of(orderDate, seller.getTimeZone());
    
        boolean isOrderDay = true; 
        while (!seller.isWorkingDayAndHour(estimatedDate, isOrderDay) || holidayCalendar.isHoliday(estimatedDate.toLocalDate())) {
            
            estimatedDate = estimatedDate.plusDays(1).withHour(seller.getStartHour(estimatedDate.getDayOfWeek())).withMinute(0);
            isOrderDay = false; 
        }
    
        int avgDeliveryDays = seller.getAverageDeliveryDays();
    
        int additionalHours = (int) Math.floor(distance / 60); 
        estimatedDate = estimatedDate.plusHours(additionalHours);
        
        estimatedDate = deliveryMethod.calculateDeliveryDate(estimatedDate, avgDeliveryDays);
        System.out.println("Дата доставки: " + estimatedDate);
    
        return estimatedDate.withZoneSameInstant(myTimeZone);
    }
}


