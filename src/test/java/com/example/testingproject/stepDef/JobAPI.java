package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class JobAPI {
    String tkn;
    Response response;
    String user_id;
    String job_id;
    @Given("I am an authenticated user with username \"\"user1\"\" and password \"\"pass1\"\"")
    public void i_am_an_authenticated_user_with_username_user1_and_password_pass1() {
       String username= ConfigurationReader.getProperty("username");
         String password=ConfigurationReader.getProperty("password");
         Map<String,String> bdy=new HashMap<>();
            bdy.put("username",username);
            bdy.put("password",password);

         response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bdy)
                .when().post(ConfigurationReader.getProperty("post_login")).prettyPeek();
        String message=response.jsonPath().getString("message");
        String actualMessage="User "+username+" logged in successfully";
        Assert.assertEquals(actualMessage,message);

    }
    @Given("I have an access token")
    public void i_have_an_access_token() {
        tkn=response.jsonPath().getString("token");
        user_id= response.jsonPath().getString("user.id");
    }
    @When("I create a job with details \"\\{\"title\": \"test\", \"company\": \"test\",\"location\": \"test\",\"description\": \"test\",\"requirements\": \"test\" }\"")
    public void i_create_a_job_with_details() {
        Map<String, String> jobDetailsMap = new HashMap<>();
        jobDetailsMap.put("title", "test");
        jobDetailsMap.put("company", "test");
        jobDetailsMap.put("location", "test");
        jobDetailsMap.put("description", "test");
        jobDetailsMap.put("requirements", "test");
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", tkn)
                .and().body(jobDetailsMap)
                .when().post("/api/"+user_id+"/jobs").prettyPeek();
        job_id=response.jsonPath().getString("data.id");



    }
    @Then("I should receive a confirmation with job details \"\\{\"title\": \"test\", \"company\": \"test\",\"location\": \"test\",\"description\": \"test\",\"requirements\": \"test\" }\"")
    public void i_should_receive_a_confirmation_with_job_details() {
        String message=response.jsonPath().getString("message");
        String actualMessage="Inserted job with id "+job_id;
        Assert.assertEquals(actualMessage,message);
    }
    @When("I update the job with new details \"\\{\"title\": \"test2\", \"company\": \"test2\",\"location\": \"test2\",\"description\": \"test2\",\"requirements\": \"test2\" }\"")
    public void i_update_the_job_with_new_details() {
        Map<String, String> jobDetailsMap = new HashMap<>();
        jobDetailsMap.put("title", "test2");
        jobDetailsMap.put("company", "test2");
        jobDetailsMap.put("location", "test2");
        jobDetailsMap.put("description", "test2");
        jobDetailsMap.put("requirements", "test2");
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", tkn)
                .and().body(jobDetailsMap)
                .when().put("/api/"+user_id+"/jobs/"+job_id).prettyPeek();
    }
    @Then("I should receive a confirmation with updated job details \"\\{\"title\": \"test2\", \"company\": \"test2\",\"location\": \"test2\",\"description\": \"test2\",\"requirements\": \"test2\" }\"")
    public void i_should_receive_a_confirmation_with_updated_job_details() {
        String message=response.jsonPath().getString("message");
        String actualMessage="Updated job with id "+job_id;
        Assert.assertEquals(actualMessage,message);
    }
    @When("I delete the job with details \"\\{\"title\": \"test2\", \"company\": \"test2\",\"location\": \"test2\",\"description\": \"test2\",\"requirements\": \"test2\" }\"")
    public void i_delete_the_job_with_details() {
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().header("Authorization", tkn)
                .when().delete("/api/"+user_id+"/jobs/"+job_id).prettyPeek();
    }
    @Then("the job \"\\{\"title\": \"test2\", \"company\": \"test2\",\"location\": \"test2\",\"description\": \"test2\",\"requirements\": \"test2\" }\" should no longer exist")
    public void the_job_should_no_longer_exist() {
        String message=response.jsonPath().getString("message");
        String actualMessage="Deleted job with id "+job_id;
        Assert.assertEquals(actualMessage,message);
    }
}
