package UserTests;

import UserUtils.CreateUsersWithListUtil;
import UserUtils.LogoutUserUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class LogoutTests {
    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
public void LogutUserTest(String data) {

    SoftAssert softAssert = new SoftAssert();
    String Id[]=data.split(",");
    Response response = CreateUsersWithListUtil.CreatUser(Id[0] , Id[1], Id[2],200);
    System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
    JsonPath jsonPath = response.jsonPath();
    String message = jsonPath.getString("message");
    softAssert.assertEquals(message,"ok");
    String Username=Id[1];
    String Userpassword=Id[2];

    Response response1 = LogoutUserUtils.logoutUser(200);
    System.out.println("Response JSON Body2: " + response1.getBody().asString());//print response body
    softAssert.assertEquals(jsonPath.getString("message"), "ok");

}





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
