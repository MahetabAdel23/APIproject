package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserbyUsernameUtils {
    public static Response GetUserName(String UserName,int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .get(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER +"/"+UserName).then().statusCode(StatusCode).extract().response();
    }
}
