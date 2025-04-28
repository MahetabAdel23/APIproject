package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LogoutUserUtils {
    public static Response logoutUser(int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .get(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER+"/logout").then().statusCode(StatusCode).extract().response();
    }
}
