package generators;

import models.Courier;

import static utils.Utils.randomString;

public class CourierGenerator {
    public static Courier randomCourier() {
        return new Courier()
                .withLogin(randomString(8))
                .withPassword(randomString(10))
                .withFirstName(randomString(3));
    }

    public static Courier randomCourierWithoutLogin() {
        return new Courier()
                .withPassword(randomString(5))
                .withFirstName(randomString(2));
    }

    public static Courier randomCourierWithoutPassword() {
        return new Courier()
                .withLogin(randomString(3))
                .withPassword(randomString(0))
                .withFirstName(randomString(6));
    }

}