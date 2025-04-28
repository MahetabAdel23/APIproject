package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginUserUtils {
    public static Response loginNewUser(String UserName,String UserPassword,int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("username", UserName)
            .queryParam("password", UserPassword)
                .get(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER+"/login").then().statusCode(StatusCode).extract().response();
    }
}
