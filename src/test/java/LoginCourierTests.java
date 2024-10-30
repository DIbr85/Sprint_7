import clients.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static generators.CourierGenerator.*;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTests {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Вход зарегистрированного курьера в систему")
    @Description("Проверка успешного входа в систему зарегистрированного курьера с валидными данными")
    public void testLoginCourier() {
        courier = randomCourier();
        courierClient.createCourierStep(courier);
        Response response = courierClient.loginCourierStep(courier);
        response.then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Вход зарегистрированного курьера в систему без заполнения логина")
    @Description("Проверка возможности входа в систему зарегистрированного курьера без заполнения логина")
    public void testLoginCourierWithoutLogin() {
        courier = randomCourierWithoutLogin();
        courierClient.createCourierStep(courier);
        Response response = courierClient.loginCourierStep(courier);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Вход зарегистрированного курьера в систему без заполнения пароля")
    @Description("Проверка возможности входа в систему зарегистрированного курьера без заполнения пароля")
    public void testLoginCourierWithoutPassword() {
        courier = randomCourierWithoutPassword();
        courierClient.createCourierStep(courier);
        Response response = courierClient.loginCourierStep(courier);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Вход зарегистрированного курьера в систему с несуществующим логином")
    @Description("Проверка возможности входа в систему зарегистрированного курьера с несуществующим логином")
    public void testLoginCourierWithNonexistentLogin() {
        courier = randomCourier();
        courierClient.loginCourierStep(courier);
        Response response = courierClient.loginCourierWithNonexistentLoginStep(courier);
        response.then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Вход зарегистрированного курьера в систему с несуществующим паролем")
    @Description("Проверка возможности входа в систему зарегистрированного курьера с несуществующим паролем")
    public void testLoginCourierWithNonexistentPassword() {
        courier = randomCourier();
        courierClient.loginCourierStep(courier);
        Response response = courierClient.loginCourierWithNonexistentPasswordStep(courier);
        response.then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        courierClient.deleteCourierStep(courier);
    }
}
