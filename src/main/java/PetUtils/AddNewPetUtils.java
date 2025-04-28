package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AddNewPetUtils {

    public static Response AddPet(String GeneralId, String CategoryId, String TagsId,int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": "+GeneralId+",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": "+CategoryId+",\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": "+TagsId+",\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .post(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET)
                .then().statusCode(StatusCode).extract().response();

    }
}
