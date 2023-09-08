package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.BrowserUtils;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplyJob {
    @When("I click on apply button")
    public void i_click_on_apply_button() {
        WebElement element = Driver.getDriver().findElement(By.xpath("//span[.='If you applied, click here!']"));
        if(element.isDisplayed()){
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
        }


    }
    @Then("I should see the today's date in the date")
    public void i_should_see_the_today_s_date_in_the_date() {
        BrowserUtils.waitFor(5);
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(5);

        WebElement element = Driver.getDriver().findElement(By.xpath("//span[contains(text(),'Applied At')]"));
       String text="";
        if(element.isDisplayed()){
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            //javascript executor to get text
            text = js.executeScript("return arguments[0].textContent;", element).toString();
        }

        LocalDate today = LocalDate.now(); // Get current date
        String s = text.split(" ")[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        Assert.assertEquals(s, formattedDate);
    }
}
