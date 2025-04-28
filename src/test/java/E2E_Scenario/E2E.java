package E2E_Scenario;

import PetUtils.AddNewPetUtils;
import PetUtils.GetPetByIdUtils;
import StoreUtils.GetOrderUtils;
import StoreUtils.PlaceAnOrderForPetUtils;
import UserUtils.CreateUserUtils;
import UserUtils.GetUserbyUsernameUtils;
import UserUtils.LoginUserUtils;
import UserUtils.LogoutUserUtils;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class E2E {
    ExtentTest test;

@Test(groups ={ "RegressionTesting","SmokeTesting"})
public void E2ETest() throws Exception {

    String UserId="";
    String UserName="";
    String UserPassword="";

    String GeneralId="";
    String CategoryId="";
    String TagsId="";

    String OrderId="";
    String PetId="";
    String Quantity="";
    //parsing the file
    JSONParser jsonParser= new JSONParser();
    FileReader reader=new FileReader("src/test/java/E2E_Scenario/Data.json");
    Object obj=jsonParser.parse(reader);
    JSONObject jsonObject= (JSONObject) obj;//will hold all data readed

    JSONArray arrayAddPet=(JSONArray) jsonObject.get("AddPetData");
    //String arrAddPet[]=new String[arrayAddPet.size()];
    for(int i=0;i<arrayAddPet.size();i++) {
        JSONObject users = (JSONObject) arrayAddPet.get(i);
         GeneralId = (String) users.get("GeneralId");
         CategoryId = (String) users.get("CategoryId");
         TagsId = (String) users.get("TagsId");
    }

    JSONArray arrayCreateUser=(JSONArray) jsonObject.get("CreateUser");
    for(int i=0;i<arrayCreateUser.size();i++)
    {
        JSONObject users=(JSONObject) arrayCreateUser.get(i);
         UserId = (String) users.get("UserId");
         UserName = (String) users.get("UserName");
         UserPassword = (String) users.get("UserPassword");
    }

    JSONArray array = (JSONArray) jsonObject.get("PlaceOrderPetData");
    for (int i = 0; i < array.size(); i++) {
        JSONObject users = (JSONObject) array.get(i);
        OrderId = (String) users.get("OrderId");
        PetId = (String) users.get("PetId");
        Quantity = (String) users.get("Quantity");
    }


        SoftAssert softAssert=new SoftAssert();
        /*addpet*/
        Response response1= AddNewPetUtils.AddPet(GeneralId, CategoryId, TagsId,200);
        softAssert.assertEquals(response1.jsonPath().getString("id"),GeneralId);
        Thread.sleep(2000);
        /*getpetbyid*/
        Response response2= GetPetByIdUtils.GetPetId(GeneralId, 200);
        softAssert.assertEquals(response2.jsonPath().getString("id"),GeneralId);
        Thread.sleep(2000);
        /*createuser*/
        Response response3 = CreateUserUtils.CreatUser(UserId, UserName,UserPassword, 200);
        System.out.println("Response JSON Body3: " + response3.getBody().asString());//print response body
        softAssert.assertEquals( response3.jsonPath().getString("message"),UserId);
    Thread.sleep(2000);
        /*GetUserbyUsername*/
        Response response4= GetUserbyUsernameUtils.GetUserName(UserName,200);
        System.out.println("Response JSON Body4: " + response4.getBody().asString());//print response body
        softAssert.assertEquals(response4.jsonPath().getString("id"),UserId);
        softAssert.assertEquals(response4.jsonPath().getString("username"),UserName);
        softAssert.assertEquals(response4.jsonPath().getString("password"),UserPassword);
    Thread.sleep(2000);   /*login usr*/
        Response response5 = LoginUserUtils.loginNewUser(UserName, UserPassword,200);
        System.out.println("Response JSON Body5: " + response5.getBody().asString());//print response body
        softAssert.assertEquals(response5.jsonPath().getString("message").startsWith("logged in user session:"), true);
    Thread.sleep(2000);   /*PlaceAnOrderForPet*/
        Response response6 = PlaceAnOrderForPetUtils.AddPet(OrderId , PetId, Quantity,200);
        System.out.println("Response JSON Body6: " + response6.getBody().asString());//print response body
        softAssert.assertEquals(response6.jsonPath().getString("id"),OrderId);
    Thread.sleep(4000);   /*GetOrder*/
        Response response7= GetOrderUtils.GetOrderId(OrderId,200);
        System.out.println("Response JSON Body7: " + response7.getBody().asString());
    Thread.sleep(2000);  /*LogoutUser*/
        Response response8 = LogoutUserUtils.logoutUser(200);
        System.out.println("Response JSON Body8: " + response8.getBody().asString());//print response body
        softAssert.assertEquals(response8.jsonPath().getString("message"), "ok");

        softAssert.assertAll();
    }


}

