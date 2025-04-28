package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetPetByID_DeleteTestUtils {

    public static Response GetPetDeleteById(String PetID, int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .get(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET+ "/"+PetID).then().statusCode(StatusCode).extract().response();
    }
}
