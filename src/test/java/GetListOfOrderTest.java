import clients.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetListOfOrderTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка получения списка заказов")
    public void ordersListTest() {
        Response response = orderClient.getListOfOrdersStep();
        response.then()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}