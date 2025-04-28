package PetTests;

import PetUtils.AddNewPetUtils;
import PetUtils.GetPetByID_DeleteTestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class GetPetByID_DeleteTests {
    //1
    String PetId="";
    @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
    public void NotDeletedPetTest(String data) throws Exception {
        SoftAssert softAssert = new SoftAssert();
        String Id[]=data.split(",");
        Response response= AddNewPetUtils.AddPet(Id[0] , Id[1], Id[2],200);
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        PetId = jsonPath.getString("id");
        System.out.println("Response JSON Body: " + response.getBody().asString());//print response body
        Thread.sleep(5000);
        Response response2 = GetPetByID_DeleteTestUtils.GetPetDeleteById(PetId,200);
        System.out.println("Response JSON Body: " + response2.getBody().asString());//print response body
        JsonPath jsonPath2 = response2.jsonPath();
        softAssert.assertEquals(jsonPath2.getString("id"), PetId);
        softAssert.assertAll();
        Thread.sleep(5000);

    }

    @DataProvider(name="json_parsing")
    public String[] jsonReader() throws Exception
    {
        //parsing the file
        JSONParser jsonParser= new JSONParser();
        FileReader reader=new FileReader("src/test/java/PetTests/PetTestDatajson.json");
        Object obj=jsonParser.parse(reader);
        JSONObject jsonObject= (JSONObject) obj;//will hold all data readed

        JSONArray array=(JSONArray) jsonObject.get("AddPetData");
        String arr[]=new String[array.size()];
        for(int i=0;i<array.size();i++)
        {
            JSONObject users=(JSONObject) array.get(i);
            String GeneralId=(String) users.get("GeneralId");
            String CategoryId=(String) users.get("CategoryId");
            String TagsId=(String) users.get("TagsId");
            arr[i]=GeneralId+","+CategoryId+","+TagsId;
        }
        return arr;
    }
}
