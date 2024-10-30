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



public class CreateCourierTests {
    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверка успешного создания нового курьера")
    public void testCreateCourier() {
        courier = randomCourier();
        Response response = courierClient.createCourierStep(courier);
        response.then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Повторное создание уже существующего курьера")
    @Description("Проверка возможности создания дубликата уже существующего курьера")
    public void testCreateDuplicateCourier() {
        courier = randomCourier();
        courierClient.createCourierStep(courier);
        Response response = courierClient.createCourierStep(courier);
        response.then()
                .statusCode(equalTo(SC_CONFLICT))
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без указания логина")
    @Description("Проверка возможности создания курьера без указания логина")
    public void testCreateCourierWithoutLogin() {
        courier = randomCourierWithoutLogin();
        Response response = courierClient.createCourierStep(courier);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без указания пароля")
    @Description("Проверка возможности создания курьера без указания пароля")
    public void testCreateCourierWithoutPassword() {
        courier = randomCourierWithoutPassword();
        Response response = courierClient.createCourierStep(courier);
        response.then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        courierClient.deleteCourierStep(courier);
    }

}
