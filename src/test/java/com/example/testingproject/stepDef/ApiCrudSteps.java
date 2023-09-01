package com.example.testingproject.stepDef;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;


public class ApiCrudSteps {
    String token;
    String userName;
    String password;
    Response response;
    @When("I register a new user with following details:")
    public void i_register_a_new_user_with_following_details(List<Map<String, String>> dataTable) {
       Map<String,String> bdy = dataTable.get(0);
       userName=bdy.get("userName");
         password=bdy.get("password");
        response= given()
                .contentType(ContentType.JSON)
                .body(bdy)
                .when()
                .post("/api/users/register").prettyPeek();
    }
    @Then("I get the register message User {string} registered successfully")
    public void i_get_the_register_message_user_registered_successfully(String msg) {
        response.then().assertThat().statusCode(200);
    }
    @When("I login to the API as new user")
    public void i_login_to_the_api_as_new_user() {
        Map<String, String> bdy= new HashMap<>();
        bdy.put("username",userName);
        bdy.put("password",password);
        response= given()
                .contentType(ContentType.JSON)
                .body(bdy)
                .when()
                .post("/api/users/login").prettyPeek();
    }
    @Then("I get the login message {string}")
    public void i_get_the_login_message(String msg) {

        response.then().assertThat().statusCode(200);
        token=response.jsonPath().getString("token");
    }
    @When("I get the list of users")
    public void i_get_the_list_of_users() {
        response= given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .get("/api/users");
    }
    @Then("I get the message Retrieved {string} users")
    public void i_get_the_message_retrieved_users(String msg) {
        response.then().assertThat().statusCode(200);
    }
    String id;
    @When("I get the user by id")
    public void i_get_the_user_by_id() {
        JsonPath jsonPath=response.jsonPath();
        Map<String,Object> user=jsonPath.getMap("user");
        id= (String) user.get("id");

    }
    @Then("I get the retrieved message equal Retrieved user with id {string}")
    public void i_get_the_retrieved_message_equal_retrieved_user_with_id(String msg) {
        response.then().assertThat().statusCode(200);
    }
    @When("I update the user with the following details:")
    public void i_update_the_user_with_the_following_details(List<Map<String, String>> dataTable) {
        Map<String,String> bdy = dataTable.get(0);
        response= given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(bdy)
                .when()
                .put("/api/users/"+id);
    }
    @Then("I get the update message User {string} updated successfully")
    public void i_get_the_update_message_user_updated_successfully(String msg) {
        response.then().assertThat().statusCode(200);
    }
    @When("I delete the user")
    public void i_delete_the_user() {
        response= given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .delete("/api/users/"+id);
    }
    @Then("I get delete the message User {string} deleted successfully")
    public void i_get_delete_the_message_user_deleted_successfully(String msg) {
        response.then().assertThat().statusCode(200);
    }

}
