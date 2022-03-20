package org.drools;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class BMIJSTest {

    @Test
    public void testBasePrice() {
        given()
          .body("{ \"Height\": 72, \"Mass\": 180 }")
          .contentType(ContentType.JSON)
          .when()
            .post("/demo/BMI_JS")
          .then()
            .statusCode(200)
            .body("'BMI value classification'", is("Normal range"));
    }
}
