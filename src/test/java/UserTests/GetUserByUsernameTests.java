package UserTests;

import UserUtils.CreateUsersWithListUtil;
import UserUtils.GetUserbyUsernameUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class GetUserByUsernameTests {
    String UserName="";
    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
    public void GetValidUser(String data) throws Exception{

        SoftAssert softAssert = new SoftAssert();
        String Id[]=data.split(",");
        Response response = CreateUsersWithListUtil.CreatUser(Id[0] , Id[1], Id[2],200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
       softAssert.assertEquals(message,"ok");
        UserName=Id[1];
        //System.out.println("username print"+UserName);
      Thread.sleep(2000);
        Response response1 = GetUserbyUsernameUtils.GetUserName(UserName,200);
        System.out.println("Response JSON Body2: " + response1.getBody().asString());//print response body
        JsonPath jsonPath1 = response1.jsonPath();
        softAssert.assertEquals(jsonPath1.getString("id"),Id[0]);
        softAssert.assertEquals(jsonPath1.getString("username"),Id[1]);
        softAssert.assertEquals(jsonPath1.getString("password"),Id[2]);


        softAssert.assertAll();



    }
/*
    @Test(dataProvider = "json_parsing")
    public void GetInvalidUser(String data) {

        SoftAssert softAssert = new SoftAssert();
        String Id[]=data.split(",");
        Response response = CreateUsersWithListUtil.CreatUser(Id[0] , Id[1], Id[2],200);
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.getString("message");
        softAssert.assertEquals(message,"ok");
        String UserName2="Hassan";
        //System.out.println("username print"+UserName);

        Response response1 = GetUserbyUsernameUtils.GetUserName(UserName2,200);
        System.out.println("Response JSON Body2: " + response1.getBody().asString());//print response body
        JsonPath jsonPath1 = response1.jsonPath();
        String message2 = jsonPath1.getString("message");
        softAssert.assertEquals(message2,"User not found");

        softAssert.assertAll();



    }
*/


    @DataProvider(name = "json_parsing")
    public String[] jsonReader() throws Exception {
        //parsing the file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/test/java/UserTests/UserTestDatajson.json");
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;//will hold all data readed

        JSONArray array = (JSONArray) jsonObject.get("User1Data");
        String arr[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String UserId = (String) users.get("UserId");
            String UserName = (String) users.get("UserName");
            String UserPassword = (String) users.get("UserPassword");
            arr[i] = UserId + "," + UserName + "," + UserPassword;
        }
        return arr;
    }
}
