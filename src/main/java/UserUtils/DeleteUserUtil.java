package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserUtil {
    public static Response DeleteUser(String UserNameUrl, int StatusCode)
{

    return RestAssured.given().log().all().contentType(ContentType.JSON)
            .delete(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER +"/"+UserNameUrl).then().statusCode(StatusCode).extract().response();
}
}
