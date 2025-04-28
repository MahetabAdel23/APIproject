package UserUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUsersWithListUtil {

    public static Response CreatUser(String UserId,String UserName, String UserPassword, int StatusCode)
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
                .post(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER + "/createWithList")
                .then().statusCode(StatusCode).extract().response();

    }
/*
    public static Response CreatUser2(int StatusCode) {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .body("[\n" +
                        "    {\n" +
                        "        \"id\": 11,\n" +
                        "        \"username\": \"joee\"\n" +
                        "        ,\"firstName\": \"youssef\"\n" +
                        "        ,\"lastName\": \"ahmed\"\n" +
                        "        ,\"email\": \"youssef@gmail.com\"\n" +
                        "        ,\"password\": \"joetest1\"\n" +
                        "        ,\"phone\": \"01140537849\"\n" +
                        "        ,\"userStatus\": 1\n" +
                        "    }\n" +
                        "]")
                .post(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER + "/createWithList")
                .then().statusCode(StatusCode).extract().response();
    }

 */

    public static Response CreatUserWithNoBody(int StatusCode)
    {
        return RestAssured.given().log().all().contentType(ContentType.JSON)
                .post(ConstantsUser.PetStore_BASE_URL + ConstantsUser.ENDPOINT_USER + "/createWithList")
                .then().statusCode(StatusCode).extract().response();

    }

}
