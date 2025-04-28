package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetPetByIdUtils {
    public static Response GetPetId(String PetID, int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .get(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET+ "/"+PetID).then().statusCode(StatusCode).extract().response();
    }
}
