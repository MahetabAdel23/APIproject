package StoreTests;

import StoreUtils.DeleteOrderUtil;
import StoreUtils.PlaceAnOrderForPetUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class DeleteOrder {

    String OrderId = "";
//3
    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
    public void GetOrderByIdTest(String data){
        SoftAssert softAssert = new SoftAssert();
        String Id[]=data.split(",");
        Response response = PlaceAnOrderForPetUtils.AddPet(Id[0] , Id[1], Id[2],200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        OrderId= jsonPath.getString("id");
        System.out.println("OrderId:  "+ OrderId);
        softAssert.assertEquals(jsonPath.getString("id"),Id[0]);

        Response response2= DeleteOrderUtil.DeleteOrder(OrderId,200);
        System.out.println("Response2 JSON Body: " + response2.getBody().asString());//print another pet


        softAssert.assertAll();
    }

    @Test(groups = {"RegressionTesting","NegativeTC"})
    public void DeleteNonExistingOrderTest() {//pass 1out of 5
        SoftAssert softAssert= new SoftAssert();
        Response response2 = DeleteOrderUtil.DeleteOrder("1234568",404);
        JsonPath jsonPath= response2.jsonPath();
        System.out.println("Response JSON Body: " + response2.getBody().asString());//print response body
        softAssert.assertEquals(jsonPath.getString("message"), "Order Not Found");
        softAssert.assertAll();
    }
    @Test(groups = {"RegressionTesting","NegativeTC"})
public void DeleteOrderWithInvalidIDTest() {
    SoftAssert softAssert= new SoftAssert();
    Response response2 = DeleteOrderUtil.DeleteOrder("dc",404);
    JsonPath jsonPath= response2.jsonPath();
    System.out.println("Response JSON Body: " + response2.getBody().asString());//print response body
    softAssert.assertTrue(response2.jsonPath().getString("message").contains("java.lang.NumberFormatException"), "Message does not contain the expected exception");
    softAssert.assertAll();
}




    @DataProvider(name = "json_parsing")
    public String[] jsonReader() throws Exception {
        //parsing the file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/test/java/StoreTests/StoreTestDatajson.json");
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;//will hold all data readed

        JSONArray array = (JSONArray) jsonObject.get("Order2Data");
        String arr[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String OrderId = (String) users.get("OrderId");
            String PetId = (String) users.get("PetId");
            String Quantity = (String) users.get("Quantity");
            arr[i] = OrderId + "," + PetId + "," + Quantity;
        }
        return arr;
    }
}
