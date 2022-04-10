package training;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.util.RestAssuredUtil;

import static io.restassured.RestAssured.given;

public class APITests extends RestAssuredUtil {

    @BeforeAll
    public static void setConfiguration(){
        String basePath = reader.readProperty("api", "BASE_PATH");
        setBasePath(basePath);
    }
    @Test
    public void getCategories() {

        String endpoint = "/category/read.php";
        ValidatableResponse response = given()
        .when().get(endpoint).then();

        response.log().body();
    }

    @Test
    public void getSingleProduct() {
        String endpoint = "/product/read_one.php";
        ValidatableResponse response = given()
                .queryParam("id", 3)
                .when()
                .get(endpoint)
                .then();

        response.log().body();
    }

    @Test
    public void getCProducts() {
        String endpoint = "/product/read.php";
        ValidatableResponse response = given().when().get(endpoint).then();

        response.log().body();

    }

}
