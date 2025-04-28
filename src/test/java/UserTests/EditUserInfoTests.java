package UserTests;

import UserUtils.CreateUsersWithListUtil;
import UserUtils.EditUserInfoUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class EditUserInfoTests {

    @Test(dataProvider = "json_parsing",groups = {"RegretionTesting","SmokeTesting"})
    public void EditUserWithValidData(String data) throws Exception {

        SoftAssert softAssert = new SoftAssert();
        String Id[] = data.split(",");
        Response response = CreateUsersWithListUtil.CreatUser("44", "ewis", "78788", 200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        softAssert.assertEquals(message, "ok");
        String UserName1="ewis";
        Thread.sleep(5000);
        Response response1 = EditUserInfoUtil.UpdateUser(UserName1,"80", "hmada", "444","1", 200);
        System.out.println("Response JSON Body After Edited: " + response1.getBody().asString());//print response body

        String editUserId="80";
        softAssert.assertEquals( response1.jsonPath().getString("message"), editUserId);
        softAssert.assertAll();

    }
/*

    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
    public void EditCreateUserTest(String data) {

        SoftAssert softAssert = new SoftAssert();
        String Id[]=data.split(",");
        Response response = CreateUserUtils.CreatUser(Id[0], Id[1], Id[2], 200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        softAssert.assertEquals( jsonPath.getString("message"),Id[0]);
       String username=Id[1];

        Response response1 = EditUserInfoUtil.UpdateUser(username,"80", Id[1], Id[2],"1", 200);
        System.out.println("Response JSON Body After Edited: " + response1.getBody().asString());//print response body
        softAssert.assertEquals(response1.jsonPath().getString("id"), Id[0]);
    }


    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
    public void EditUserWithValidData2(String data) {

        SoftAssert softAssert = new SoftAssert();
        String Id[] = data.split(",");
        Response response = CreateUsersWithListUtil.CreatUser(Id[0], Id[1], Id[2], 200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        softAssert.assertEquals(message, "ok");
        String UserName1 = Id[1];

        Response response1 = EditUserInfoUtil.UpdatePet2(UserName1, Id[0],"maher", Id[2], 200);
        System.out.println("Response JSON Body After Edited: " + response1.getBody().asString());//print response body
        JsonPath jsonPath2 = response.jsonPath();
        softAssert.assertEquals(jsonPath2.getString("id"), Id[0]);
        softAssert.assertAll();
    }


    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","NegativeTC"})
    public void EditUserWithInvalidDataNoBody(String data) {

        SoftAssert softAssert = new SoftAssert();
        String Id[] = data.split(",");
        Response response = CreateUsersWithListUtil.CreatUser(Id[0], Id[1], Id[2], 200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        softAssert.assertEquals(message, "ok");
        String UserName1 = Id[1];
        Response response1 = EditUserInfoUtil.UpdatePetWithInvalidNoBody("maher",404);
        System.out.println("Response JSON Body After Edited: " + response1.getBody().asString());//print response body
        JsonPath jsonPath2 = response.jsonPath();

        softAssert.assertAll();

    }*/




        @DataProvider(name = "json_parsing")
    public String[] jsonReader() throws Exception {
        //parsing the file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/test/java/UserTests/UserTestDatajson.json");
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;//will hold all data readed

        JSONArray array = (JSONArray) jsonObject.get("User33Data");
        String arr[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String UserId = (String) users.get("UserId");
            String UserName = (String) users.get("UserName");
            String UserPassword = (String) users.get("UserPassword");
            String PhoneNo = (String) users.get("PhoneNo");
            arr[i] = UserId + "," + UserName + "," + UserPassword +"," + PhoneNo;
        }
        return arr;
    }

}
