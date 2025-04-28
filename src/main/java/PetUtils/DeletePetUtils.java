package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeletePetUtils {
    public static Response DeletePet(String PetID, int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .delete(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET+ "/"+PetID).then().statusCode(StatusCode).extract().response();
    }
}
