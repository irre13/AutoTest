package accuweather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seminar.accuweather.weather.Weather;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ForecastsTenDaysTest extends AccuweatherAbstractTest {

    @Test
    void testGetTenDayForecastResponse() {
        // Изменено на 10day в запросе URL
        Weather weather = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/10day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        // Изменено условие ожидания на 10 дней
        Assertions.assertEquals(10, weather.getDailyForecasts().size());
        System.out.println(weather);
    }
}