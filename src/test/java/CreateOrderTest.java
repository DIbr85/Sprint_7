import clients.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static generators.OrderGenerator.randomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Parameterized.Parameters
    public static Object[][] data () {
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание нового заказа")
    @Description("Проверка успешного создания заказа")
    public void createOrderTest() {
        Order order = randomOrder().withColor(color);
        OrderClient orderClient = new OrderClient();
        Response response = orderClient.createOrderStep(order);
        response.then()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }
}