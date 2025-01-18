package br.com.erudio.integrationtests.jsontest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.Application;
import br.com.erudio.configs.TestsConfigs;
import br.com.erudio.integrationtests.AbstractIntegrationTest;
import br.com.erudio.integrationtests.vos.PersonVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = Application.class)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {
    
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonVO person;
    
    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.findAndRegisterModules();
        
        person = new PersonVO();
        mockPerson();
        
        specification = new RequestSpecBuilder()    
                .addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, "http://localhost:8080")
                .setBasePath("/v1/person")
                .setPort(8080)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    void testCreate() throws JsonProcessingException {
        var content = given().spec(specification)
            .contentType(TestsConfigs.CONTENT_TYPE_JSON)
            .body(person)
            .when()
                .post()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();
        
        PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
        person = createdPerson;
        
        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getName());
        assertNotNull(createdPerson.getAge());
        assertNotNull(createdPerson.getCountry());
        
        assertEquals("Johnny Test", createdPerson.getName());
        assertEquals(27, createdPerson.getAge());
        assertEquals("USA", createdPerson.getCountry());
    }

    private static void mockPerson() {
        person.setName("Johnny Test");
        person.setAge(27);
        person.setCountry("USA");
    }
}
