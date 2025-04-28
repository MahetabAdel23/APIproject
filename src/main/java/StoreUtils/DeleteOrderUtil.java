package StoreUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteOrderUtil {
    public static Response DeleteOrder(String PetID, int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .delete(ConstantsStore.PetStore_BASE_URL+ConstantsStore.ENDPOINT_STORE_ORDER+ "/"+PetID).then().statusCode(StatusCode).extract().response();
    }
}
