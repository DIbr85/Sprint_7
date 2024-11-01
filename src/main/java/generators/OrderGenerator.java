package generators;

import models.Order;

import static utils.Utils.*;

public class OrderGenerator {
    public static Order randomOrder() {
        return new Order()
                .withFirstName(randomString(14))
                .withLastName(randomString(14))
                .withAddress(randomString(14))
                .withMetroStation(randomNumber(2))
                .withPhone(randomNumber(11))
                .withRentTime(randomInt(2))
                .withDeliveryDate("2024-10-29")
                .withComment(randomString(20));
    }
}