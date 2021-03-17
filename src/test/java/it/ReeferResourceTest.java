package it;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ReeferResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/reefers")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}