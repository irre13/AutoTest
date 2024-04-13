package accuweatherWithWireMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class ForecastOneDayTest extends AccuweatherWireMockWithRestAssuredAbstractTest {

    @BeforeEach
    void setupStub() {
        // Настройка мок-ответа на определенный запрос
        stubFor(get(urlEqualTo("/forecasts/v1/daily/1day/50"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("{'dailyForecasts': [{'day': 'Sunny'}]}"))); // Примерный ответ, модифицируйте под ваши данные
    }

    @Test
    void testGetResponse() {
        // Тестирование с использованием RestAssured и мока WireMock
        given()
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/50")
                .then()
                .statusCode(200)
                .log().all();
    }
}
