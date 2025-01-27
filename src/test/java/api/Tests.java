package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tests {

    String url = "https://reqres.in/";
    String successfullLoginTestRequestBody = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"cityslicka\"\n" +
            "}";
    String unsuccessfullLoginTestRequestBody = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "}";

    String timeBody = "{\n" +
            "    \"name\": \"eve.holt@reqres.in\",\n" +
            "    \"job\": \"cityslicka\"\n" +
            "}";

    @Test
    public void successfullLoginTestRequestBody() {
        Specifications.installSpec(Specifications.requestSpec(url), Specifications.responseSpecOK200());
        given()
                .body(successfullLoginTestRequestBody)
                .when()
                .post("/api/login")
                .then()
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void unsuccessfullLoginTest() {
        Specifications.installSpec(Specifications.requestSpec(url), Specifications.responseSpecError400());
        given()
                .body(unsuccessfullLoginTestRequestBody)
                .when()
                .post("/api/login");
    }

    @Test
    public void domenListUsersTest() {
        Specifications.installSpec(Specifications.requestSpec(url), Specifications.responseSpecOK200());
        List<ListUsers> listUsers = given()
                .param("page", 2)
                .when()
                .get("/api/users")
                .then()
                .extract().jsonPath().getList("data", ListUsers.class);

        Assert.assertTrue(listUsers.stream().allMatch(user -> user.email.endsWith("@reqres.in")));
    }

    @Test
    public void deleteUser() {
        Specifications.installSpec(Specifications.requestSpec(url), Specifications.responseSpecOK204());
        given()
                .when()
                .delete("/api/users/2");
    }

    @Test
    public void patchUser() {
        Specifications.installSpec(Specifications.requestSpec(url), Specifications.responseSpecOK200());
        TimeResponse response = given()
                .body(timeBody)
                .baseUri("https://reqres.in/")
                .when()
                .patch("/api/users/2")
                .then().log().all()
                .extract().as(TimeResponse.class);

        String responseDate = response.updatedAt.substring(2, 16);
        String SystemDate = Clock.systemUTC().instant().toString().substring(2, 16);

        Assert.assertEquals(responseDate, SystemDate);
    }
}
