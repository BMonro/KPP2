package org.example;

import java.time.ZonedDateTime;

public class StandardDelivery implements DeliveryMethod {
    @Override
    public ZonedDateTime calculateDeliveryDate(ZonedDateTime estimatedDate, int averageDeliveryDays) {
        
        ZonedDateTime newEstimatedDate = estimatedDate.plusDays(averageDeliveryDays);
        
        return newEstimatedDate; 
    }
}

