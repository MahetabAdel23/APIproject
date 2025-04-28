package PetTests;

import PetUtils.AddNewPetUtils;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;

public class UpdatePetTests {



        @Test(dataProvider = "json_parsing",groups = {"RegressionTesting","SmokeTesting"})
        public void UpdatePetSuccessfuly(String data) {

            SoftAssert softAssert = new SoftAssert();
            String Id[] = data.split(",");
            Response response = AddNewPetUtils.AddPet(Id[0], Id[1], Id[2], 200);
            softAssert.assertEquals(response.jsonPath().getString("id"), Id[0]);




            softAssert.assertAll();
        }



    @DataProvider(name = "json_parsing")
    public String[] jsonReader() throws Exception {
        //parsing the file
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/test/java/PetTests/PetTestDatajson.json");
        Object obj = jsonParser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;//will hold all data readed

        JSONArray array = (JSONArray) jsonObject.get("UpdatePetData");
        String arr[] = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject users = (JSONObject) array.get(i);
            String GeneralId = (String) users.get("GeneralId");
            String CategoryId = (String) users.get("CategoryId");
            String TagsId = (String) users.get("TagsId");
            arr[i] = GeneralId + "," + CategoryId + "," + TagsId;
        }
        return arr;
    }
}



