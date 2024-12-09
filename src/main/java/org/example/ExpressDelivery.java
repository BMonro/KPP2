package org.example;

import java.time.ZonedDateTime;

public class ExpressDelivery implements DeliveryMethod {
    @Override
    public ZonedDateTime calculateDeliveryDate(ZonedDateTime estimatedDate, int averageDeliveryDays) {
        return estimatedDate.plusDays(averageDeliveryDays/3); 
    }
}
