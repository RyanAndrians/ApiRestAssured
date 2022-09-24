package Core.Engine;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Base {
    static String urlApi = "https://api.punkapi.com/v2/beers?page=2&per_page=";
    static String urlCheckScheme = "https://api.punkapi.com/v2/beers";
    public static void getResponseBody(String numberPage) {
        given().when().get(urlApi+numberPage).then().statusCode(HttpStatus.SC_OK).extract().response();
    }

    public static void assertResponseBody(String numberPage){
        get(urlApi+numberPage).then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("size()", is(Integer.parseInt(numberPage)));
    }

    public static void checkSchema(){
        RestAssured.baseURI = urlCheckScheme ;
        given()
                .when().get()
                .then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File("src/main/java/Core/Schema/Schema.json")));
    }

    public static void hitUrl(String url){
        given().when().get(url).statusCode();
    }
}
