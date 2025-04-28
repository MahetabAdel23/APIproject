package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUsingFormUtils {
    public static Response UpdateUsingForm(String id, int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.URLENC)
                .formParams("status","avilable")
                .formParams("name","dog")


                .post(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET+"/"+id)
                .then().statusCode(StatusCode).extract().response();

    }
}
