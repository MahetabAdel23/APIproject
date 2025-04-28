package PetUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetPetByStatusUtils {
    public static Response GetPetId(String StatusState, int StatusCode)
{
    String ENDPOINT_GETPETSTATUS="/findByStatus";
    return RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("status",StatusState)
            .get(Constants.PetStore_BASE_URL +Constants.ENDPOINT_PET+ENDPOINT_GETPETSTATUS).then().statusCode(StatusCode).extract().response();
}
}
