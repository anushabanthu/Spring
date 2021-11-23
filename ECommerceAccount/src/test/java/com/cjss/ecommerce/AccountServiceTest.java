package com.cjss.ecommerce;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Test
    public void testAPIs() throws InterruptedException {
        String login = "{\"email\":\"anusha.banthu@gmail.com\"," +
                "\"password\":\"123456\"}";
        String loginWrongPassword = "{\"email\":\"anusha.banthu@gmail.com\"," +
                "\"password\":\"1\"}";
        String registerCustomerPayload = "{" +
                "\"firstName\":\"anusha\"," +
                "\"lastName\":\"banthu\"," +
                "\"email\":\"anusha.banthu@gmail.com\"," +
                "\"mobileNumber\":9845981325," +
                "\"password\":\"123456\","+
                "\"addresses\":[{" +
                "\"line1\": \"DNO:1\","+
                "\"line2\": \"street1\","+
                "\"postalCode\": 111,"+
                "\"state\": \"state1\","+
                "\"city\": \"city1\","+
                "\"shippingAddress\": true,"+
                "\"billingAddress\": false"+
            "},"+
            "{" +
                "\"line1\": \"DNO:2\","+
                "\"line2\": \"street2\","+
                "\"postalCode\": 222,"+
                "\"state\": \"state2\","+
                "\"city\": \"city2\","+
                "\"shippingAddress\": false,"+
                "\"billingAddress\": true"+
        "}]"+
    "}";
    String address = "{" +
            "\"id\": \"anusha.banthu@gmail.com\","+
            "\"line1\": \"DNO:1\","+
            "\"line2\": \"street1\","+
            "\"postalCode\": 111,"+
            "\"state\": \"state1\","+
            "\"city\": \"city1\","+
            "\"shippingAddress\": true,"+
            "\"billingAddress\": false"+
            "}";
    given()
            .contentType(ContentType.JSON).body(login).post("http://localhost:8081/login-user")
                    .then().statusCode(400);
    given()
            .contentType(ContentType.JSON).body(registerCustomerPayload).post("http://localhost:8081/register-customer")
            .then().statusCode(200)
            .body("email", equalTo("anusha.banthu@gmail.com")).and()
            .body("firstName", equalTo("anusha"));
    given()
            .contentType(ContentType.JSON).body(login).post("http://localhost:8081/login-user")
            .then().statusCode(200);
    given()
            .contentType(ContentType.JSON).body(login).post("http://localhost:8081/login-user")
            .then().statusCode(208);
//    wait(20000);
//    given()
//            .contentType(ContentType.JSON).body(loginWrongPassword).post("http://localhost:8081/login-user")
//            .then().statusCode(401);
    given()
            .contentType(ContentType.JSON).post("http://localhost:8081/get-customer-details-by-id?id=anusha.banthu@gmail.com")
            .then().statusCode(200)
            .body("email", equalTo("anusha.banthu@gmail.com")).and()
            .body("mobileNumber", equalTo(9845981325l));
    given()
            .contentType(ContentType.JSON).body(address).post("http://localhost:8081/add-address")
            .then().statusCode(200)
            .body("email", equalTo("anusha.banthu@gmail.com"));
    }

}
