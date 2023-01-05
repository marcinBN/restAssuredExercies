import org.testng.Assert;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Beer;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class APITestPunkApi {
    @Test
    public void testIfGetBeersRequestReturnsAtLeastOneRecord() {

        List<Beer> beers = given()
                .baseUri("https://api.punkapi.com/v2")
                .basePath("beers")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                // here's the magic
                .jsonPath().getList(".", Beer.class);
        Assert.assertTrue(beers.size() > 0, "API replied with empty list.");

    }
    @Test
    public void testIfRequestWithAbvGreaterThanSixReturnsRecordsWithAbvGreaterThanSix() {

        List<Beer> beers = given()
                .baseUri("https://api.punkapi.com/v2")
                .basePath("beers")
                .queryParam("abv_gt", "6")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                // here's the magic
                .jsonPath().getList(".", Beer.class);
        List<Beer> filteredBeers = beers.stream().filter(beer -> beer.getAbv() < 6.0).collect(Collectors.toList());
        Assert.assertTrue(filteredBeers.size() == 0, "The reponse contains items with ABV greater than 6.");

    }
}
