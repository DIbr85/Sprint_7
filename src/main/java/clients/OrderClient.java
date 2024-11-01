package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {
    @Step("Создание заказа")
    public Response createOrderStep(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получение списка заказов")
    public Response getListOfOrdersStep() {
        return given()
                .get("/api/v1/orders");
    }
}