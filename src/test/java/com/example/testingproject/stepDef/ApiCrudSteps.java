package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

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
    String email;
    Response response;
String id;
    @Given("I register a new user with following details:")
    public void i_register_a_new_user_with_following_details(List<Map<String,Object>> data) {
      Map<String,Object> bdy= data.get(0);
        userName=bdy.get("username").toString();
        password=bdy.get("password").toString();
        email=bdy.get("email").toString();
        response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bdy)
                .when().post(ConfigurationReader.getProperty("post_register"))
                .prettyPeek();
    }
    @Then("I should get a successful registration message")
    public void i_should_get_a_successful_registration_message() {
       String message=response.jsonPath().getString("message");
       String actualMessage="User "+userName+" registered successfully";
        Assert.assertEquals(actualMessage,message);
    }
    @When("I login as this user")
    public void i_login_as_this_user() {
        Map<String,Object> bdy=new HashMap<>();
        bdy.put("username",userName);
        bdy.put("password",password);
        response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bdy)
                .when().post(ConfigurationReader.getProperty("post_login"));
        token=response.jsonPath().getString("token");
    }
    @Then("I should get a successful login message")
    public void i_should_get_a_successful_login_message() {
        String message=response.jsonPath().getString("message");
        String actualMessage="User "+userName+" logged in successfully";
        Assert.assertEquals(actualMessage,message);
    }
    Response response1;
    @When("I list all registered users")
    public void i_list_all_registered_users() {
         response1 = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .when().get("/api/users")
                .prettyPeek();
    }
    @Then("I should get a user count message")
    public void i_should_get_a_user_count_message() {
        String message=response1.jsonPath().getString("message");
        String actualMessage="Retrieved "+response1.jsonPath().getList("data").size()+" users";
        //Assert.assertEquals(actualMessage,message);
    }
    @When("I retrieve the details of this user by id")
    public void i_retrieve_the_details_of_this_user_by_id() {
      id=  response.jsonPath().getString("user.id");
    }
    @Then("I should get a message with the retrieved user details")
    public void i_should_get_a_message_with_the_retrieved_user_details() {
      given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .when().get("/api/users/"+id)
                .prettyPeek().then().statusCode(200);
    }
    @Given("I have the following updated user details:")
    public void i_have_the_following_updated_user_details(List<Map<String,Object>> dataTable) {
        Map<String,Object> bdy= dataTable.get(0);
        userName=bdy.get("username").toString();
        password=bdy.get("password").toString();
        email=bdy.get("email").toString();


    }
    @When("I update this user using updated details")
    public void i_update_this_user_using_updated_details() {
        Map<String,Object> bdy=new HashMap<>();
        bdy.put("username",userName);
        bdy.put("password",password);
        bdy.put("email",email);
        response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .and().body(bdy)
                .when().put("/api/users/"+id)
                .prettyPeek();
    }
    @Then("I should get a successful update message")
    public void i_should_get_a_successful_update_message() {
        String message=response.jsonPath().getString("message");
        String actualMessage="Updated user with id "+id;
        Assert.assertEquals(actualMessage,message);
    }
    @When("I delete this user")
    public void i_delete_this_user() {
        response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", token)
                .when().delete("/api/users/"+id)
                .prettyPeek();
    }
    @Then("I should get a successful user deletion message")
    public void i_should_get_a_successful_user_deletion_message() {
        String message=response.jsonPath().getString("message");
        String actualMessage="Deleted user with id "+id;
        Assert.assertEquals(actualMessage,message);
    }





}
