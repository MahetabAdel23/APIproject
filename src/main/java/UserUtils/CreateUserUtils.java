package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserUtils {


    public static Response CreatUser(String UserId, String UserName, String UserPassword, int StatusCode)
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
                        "  \"userStatus\": 0\n" +
                        "}")
                .post(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER )
                .then().statusCode(StatusCode).extract().response();

    }
}
