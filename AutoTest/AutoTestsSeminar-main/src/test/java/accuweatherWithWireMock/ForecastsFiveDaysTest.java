package accuweatherWithWireMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class ForecastsFiveDaysTest extends AccuweatherWireMockWithRestAssuredAbstractTest {

    @BeforeEach
    void setupStub() {
        // Настройка мок-ответа на запрос пятидневного прогноза
        stubFor(get(urlEqualTo("/forecasts/v1/daily/5day/50"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        // Измененное тело ответа, представляющее пятидневный прогноз
                        .withBody("{'dailyForecasts': [{'day': 'Sunny'}, {'day': 'Cloudy'}, {'day': 'Rainy'}, {'day': 'Snowy'}, {'day': 'Windy'}]}")));
    }

    @Test
    void testGetResponse() {
        // Тестирование пятидневного прогноза с использованием RestAssured и мока WireMock
        given()
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/5day/50")
                .then()
                .statusCode(200) // Проверка на успешный статус ответа
                .log().all(); // Логирование ответа для удобства отладки
    }
}
