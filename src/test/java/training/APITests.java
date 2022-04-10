package training;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.restassured.response.ValidatableResponse;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.util.RestAssuredUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class APITests extends RestAssuredUtil {

    @BeforeAll
    public static void setConfiguration() {
        String basePath = reader.readProperty("api", "BASE_PATH");
        setBasePath(basePath);
    }

    @Test
    public void getCategories() {

        String endpoint = "/category/read.php";
        ValidatableResponse response = given()
                .when().get(endpoint).then().assertThat().statusCode(200);

        response.log().body();
    }

    @Test
    public void getDeserializedSingleProduct() {
        String endpoint = "/product/read_one.php";

        Product product = new Product(18,"Magnesium 250 mg (100 tablets)","A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals.",
                10.00,4,"Supplements");
//        ValidatableResponse response=
        Product response =
                given()
                        .queryParam("id", "18")
                        .when()
                        .get(endpoint)
                        .as(Product.class);
        assertThat(response,samePropertyValuesAs(product));
    }

    @Test
    public void getCProducts() {
        String endpoint = "/product/read.php";

        ValidatableResponse response = given().when().get(endpoint).then().log().headers()
                .assertThat()
                .statusCode(200)
                .headers("Content-Type","application/json; charset=UTF-8")
                .body("records.size()",greaterThan(0))
                .body("records.id",everyItem(notNullValue()))
                .body("records.id[0]",equalTo("18"));

        response.log().body();

    }

}
