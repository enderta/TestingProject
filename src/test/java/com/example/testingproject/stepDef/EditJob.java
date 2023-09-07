package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.BrowserUtils;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class EditJob {
    private void sendKeysWithJs(WebElement element, String value){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value= arguments[1]", element, value);
    }
    String newJobTitle;
    @When("I click on the edit button")
    public void i_click_on_the_edit_button() {
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(3);
        WebElement element = Driver.getDriver().findElement(By.xpath("//button[.='Edit']"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);


    }
    @When("I change job details {string}, {string}, {string}, {string}, {string}")
    public void i_enter_job_details(String title, String company, String description, String location, String requirements) {
        this.newJobTitle = title;
        Driver.getDriver().findElement(By.name("title")).clear();
        Driver.getDriver().findElement(By.name("title")).sendKeys(title);
        Driver.getDriver().findElement(By.name("company")).clear();
        Driver.getDriver().findElement(By.name("company")).sendKeys(company);
        Driver.getDriver().findElement(By.name("location")).clear();
        Driver.getDriver().findElement(By.name("location")).sendKeys(location);
        WebElement element = Driver.getDriver().findElement(By.name("requirements"));
        WebElement element1 = Driver.getDriver().findElement(By.name("description"));
        sendKeysWithJs(element, requirements);
        sendKeysWithJs(element1, description);

    }

    @Then("I should see the job edit to the list")
    public void i_should_see_the_job_edit_to_the_list() {
        BrowserUtils.waitFor(10);
        String text = Driver.getDriver().findElement(By.xpath("//div[@class='card-title h5']")).getText();
        Assert.assertEquals(newJobTitle,text);
    }

}
