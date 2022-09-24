package Core.Engine;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Base {
    public static String getResponseBody(String numberPage) {
        given().when().get
                ("https://api.punkapi.com/v2/beers?page=2&per_page="+numberPage)
                .then().log().all();
        return numberPage;
    }

    public static void assertResponseBody(String numberPage){
        get("https://api.punkapi.com/v2/beers?page=2&per_page="+numberPage).then()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("size()", is(Integer.parseInt(numberPage)));
    }

    public static void checkSchema(){
        RestAssured.baseURI = "https://api.punkapi.com/v2/beers";
        given()
                .when().get()
                //verify JSON Schema
                .then().assertThat()
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new File("src/main/java/Core/Schema/Schema.json")));
    }

    public static void hitUrl(String url){
        given().when().get(url).statusCode();
    }
}
