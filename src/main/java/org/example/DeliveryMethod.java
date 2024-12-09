package org.example;

import java.time.ZonedDateTime;

public interface DeliveryMethod {
    ZonedDateTime calculateDeliveryDate(ZonedDateTime estimatedDate, int averageDeliveryDays);
}

