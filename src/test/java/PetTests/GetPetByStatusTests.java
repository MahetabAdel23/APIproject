package PetTests;

import PetUtils.GetPetByStatusUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetPetByStatusTests {



    @Test(groups = {"RegressionTesting","SmokeTesting"})
    public void GetStatusTest() {
        SoftAssert softAssert=new SoftAssert();
        Response response = GetPetByStatusUtils.GetPetId("available", 200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath=response.jsonPath();
        //softAssert.assertEquals(jsonPath.getString("status"),"available");
        List<String> statuses = jsonPath.getList("status"); // Get the list of statuses
        for (String status : statuses) {
            softAssert.assertEquals(status, "available", "Status is not 'available'");//loop on list
        }

        softAssert.assertAll();

    }





}
