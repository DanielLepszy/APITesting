package training.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import reader.ReaderFileResources;

import static io.restassured.RestAssured.given;

public class RestAssuredUtil {

    public static ReaderFileResources reader = new ReaderFileResources();

    @BeforeAll
    public static void setBaseURI() {
        RestAssured.baseURI = reader.readProperty("api", "HOST_URL");
    }

    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;

    }
    public static void setRequestSpecification(RequestSpecification spec){
        RestAssured.requestSpecification = spec;
    }

    public static Response getResponse() {
        return given().get();
    }

}
