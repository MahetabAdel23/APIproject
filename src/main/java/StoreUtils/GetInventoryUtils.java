package StoreUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetInventoryUtils {
    public static Response GetInventory(int StatusCode)
    {

        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .get(ConstantsStore.PetStore_BASE_URL +ConstantsStore.ENDPOINT_STORE_INVENTORY).then().statusCode(StatusCode).extract().response();
    }
}
