import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;

public class APITestPunkApi {
    
    @Test
    public void testIfResponseContainsOnlyItemsWithABVabove6() {
        // Define endpoint with query parameter that is subject of test
        Response response = get("https://api.punkapi.com/v2/beers?abv_gt=6");

        // Get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Slice the whole response to contain only ABV attribute of each item
        // Use List of Numbers since we may approach different formats of data (floats, integers)
        List<Number> allItemsABV = jsonPathEvaluator.getList("abv");

        // Iterate through list and make assertions
        for (int i = 0; i < allItemsABV.size(); i++) {
            System.out.println(allItemsABV.get(i));
            System.out.println(allItemsABV.get(i).getClass().getName());
            System.out.println(allItemsABV.get(i).floatValue() > 6.0);
            Assert.assertTrue(allItemsABV.get(i).floatValue() > 6.0);
        }

    }
}
