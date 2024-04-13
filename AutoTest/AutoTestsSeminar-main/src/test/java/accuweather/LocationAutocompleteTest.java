package accuweather;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seminar.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class LocationAutocompleteTest extends AccuweatherAbstractTest {

    @Test
    public void testCityAutocomplete() {

        String apikey = prop.getProperty("apikey");
        String query = "New York"; // Пример запроса

        Response response = given()
                .queryParam("apikey", apikey)
                .queryParam("q", query)
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/autocomplete");

        // Проверяем, что статус код 200 OK
        Assertions.assertEquals(200, response.getStatusCode());

        // Преобразовываем ответ в список объектов Location
        List<Location> locations = response.jsonPath().getList(".", Location.class);

        // Проверяем, что список местоположений не пустой
        Assertions.assertFalse(locations.isEmpty(), "Locations list should not be empty");

        // Выводим полученные местоположения (опционально)
        locations.forEach(location -> System.out.println(location.getLocalizedName()));

    }
}