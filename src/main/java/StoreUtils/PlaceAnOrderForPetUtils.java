package StoreUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PlaceAnOrderForPetUtils {
    public static Response AddPet(String OrderId, String PetId, String Quantity, int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\":"+OrderId+",\n" +
                        "  \"petId\": "+PetId+",\n" +
                        "  \"quantity\": "+Quantity+",\n" +
                        "  \"shipDate\": \"2025-04-22T15:43:06.581Z\",\n" +
                        "  \"status\": \"sold\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .post(ConstantsStore.PetStore_BASE_URL +ConstantsStore.ENDPOINT_STORE_ORDER)
                .then().statusCode(StatusCode).extract().response();

    }
}
