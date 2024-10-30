package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;
import models.CourierCreds;
import models.CourierId;

import static io.restassured.RestAssured.given;


public class CourierClient {
    @Step("Создание курьера")
    public Response createCourierStep(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Логин курьера")
    public Response loginCourierStep(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(CourierCreds.getCourierCreds(courier))
                .when()
                .post("/api/v1/courier/login");
    }

    @Step("Получение ID курьера")
    public int getCourierId(Courier courier) {
        Response loginResponse = loginCourierStep(courier);
        return loginResponse.as(CourierId.class).getId();
    }

    @Step("Удаление созданного курьера")
    public Response deleteCourierStep(Courier courier) {
        int id = getCourierId(courier);
        return given()
                .header("Content-type", "application/json")
                .when()
                .post("/api/v1/courier" + id);
    }
    @Step
    public Response loginCourierWithNonexistentLoginStep(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(CourierCreds.getCourierCredsWithNonexistentLogin(courier))
                .when()
                .post("/api/v1/courier/login");
    }
    @Step
    public Response loginCourierWithNonexistentPasswordStep(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(CourierCreds.getCourierCredsWithNonexistentPassword(courier))
                .when()
                .post("/api/v1/courier/login");
    }

}