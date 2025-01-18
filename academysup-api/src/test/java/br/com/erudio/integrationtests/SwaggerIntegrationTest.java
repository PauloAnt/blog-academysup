package br.com.erudio.integrationtests;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.erudio.Application;
import br.com.erudio.configs.TestsConfigs;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void showDisplaySwaggerUIPage() {
        var content = given()
            .basePath("/swagger-ui/index.html")
            .port(TestsConfigs.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();
        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}
