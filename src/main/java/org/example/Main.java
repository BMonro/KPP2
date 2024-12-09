package org.example;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<DayOfWeek, WorkingHours> seller1Schedule = new HashMap<>();
        seller1Schedule.put(DayOfWeek.MONDAY, new WorkingHours(9, 17));
        seller1Schedule.put(DayOfWeek.TUESDAY, new WorkingHours(9, 17));
        seller1Schedule.put(DayOfWeek.WEDNESDAY, new WorkingHours(9, 17));
        seller1Schedule.put(DayOfWeek.THURSDAY, new WorkingHours(9, 17));
        seller1Schedule.put(DayOfWeek.FRIDAY, new WorkingHours(9, 17));

        SellerAvailability seller1 = new SellerAvailability(
                "Seller1",
                seller1Schedule,
                3,
                ZoneId.of("Europe/Kyiv")
        );

        DeliveryScheduler scheduler = DeliveryScheduler.getInstance();
        scheduler.addSeller(seller1);

        LocalDateTime orderDate = LocalDateTime.of(2024, 12, 24, 10, 0);
        String sellerName = "Seller1";
        DeliveryMethod deliveryMethod = new ExpressDelivery();
        double distance = 500.0;

        try {
            System.out.println("Обчислюємо дату доставки...");
            scheduler.calculateDeliveryDate(orderDate, sellerName, deliveryMethod, distance);
            System.out.println("");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        String text = "Here are some potential passwords: Passw0rd!, gds3f#!ssd, MoRPPs@20678, weakpassword, Abc12345@, Short1@.";
        List<String> candidates = PasswordValidator.findCandidates(text);
        System.out.println("Кандидати на паролі:");
        candidates.forEach(System.out::println);
    }
}
