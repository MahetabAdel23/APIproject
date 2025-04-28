package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EditUserInfoUtil {
    public static Response UpdateUser(String UserNameUrl, String UserId, String UserName, String UserPassword,String userStatusVariable, int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": "+UserId+",\n" +
                        "  \"username\": \""+UserName+"\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \""+UserPassword+"\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": "+userStatusVariable+"\n" +
                        "}")
                .put(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER +"/"+UserNameUrl)
                .then().statusCode(StatusCode).extract().response();

}




















    public static Response UpdatePet2(String UserNameUrl,String UserId,String UserName,String UserPassword,int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": "+UserId+",\n" +
                        "    \"username\": \""+UserName+"\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \""+UserPassword+"\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .put(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER +"/"+UserNameUrl)
                .then().statusCode(StatusCode).extract().response();

    }
    public static Response UpdatePetWithInvalidNoBody(String UserNameUrl,int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)

                .put(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER +"/"+UserNameUrl)
                .then().statusCode(StatusCode).extract().response();

    }

}
