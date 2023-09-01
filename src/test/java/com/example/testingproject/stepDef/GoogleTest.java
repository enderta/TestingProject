package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GoogleTest {
    @Given("I am on the Google homepage")
    public void i_am_on_the_google_homepage() {
        Driver.getDriver().get("https://www.google.com");
        //avaoid consent agreement
        Driver.getDriver().findElement(By.xpath("//*[.='Accept all']")).click();
    }
    @When("I search for {string}")
    public void i_search_for(String string) {
        Driver.getDriver().findElement(By.name("q")).sendKeys(string +  Keys.ENTER);
    }
    @Then("I should see a list of podcasts")
    public void i_should_see_a_list_of_podcasts() {
      Assert.assertTrue(Driver.getDriver().getTitle().contains("podcast"));
    }
}
