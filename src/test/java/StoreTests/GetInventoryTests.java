package StoreTests;

import StoreUtils.GetInventoryUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetInventoryTests {

    @Test(groups = {"RegressionTesting","SmokeTesting"})
    public void GetTest() {
        SoftAssert softAssert = new SoftAssert();
        Response response = GetInventoryUtils.GetInventory(200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        softAssert.assertNotNull(jsonPath.getInt("sold"));
        softAssert.assertAll();
    }
}