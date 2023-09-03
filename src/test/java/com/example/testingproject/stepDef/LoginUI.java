package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.ConfigurationReader;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;

public class LoginUI {
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Driver.getDriver().findElement(By.xpath("//a[.='Login']")).click();
    }
    @When("I enter username and password")
    public void i_enter_username_and_password() {
       Driver.getDriver().findElement(By.xpath("//input[@name='username']")).
               sendKeys(ConfigurationReader.getProperty("username"));
         Driver.getDriver().findElement(By.xpath("//input[@name='password']")).
                sendKeys(ConfigurationReader.getProperty("password"));
       Driver.getDriver().findElement(By.xpath("//button[.='Login']")).click();
    }
    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        System.out.println("I am logged in");
    }
}
