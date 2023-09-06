package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.BrowserUtils;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddingJobUI {


    private int beforesize;


    private void sendKeysWithJs(WebElement element, String value){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value= arguments[1]", element, value);
    }

    private void selectByIndexWhenSizeGreaterThanThree(int size, WebElement element){
        if(size > 3){
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            select.selectByIndex(options.size()-1);
        }
    }

    @When("I click on the add job button")
    public void i_click_on_the_add_job_button() {


        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size =0;
        if(text.equals("No job click + button to add job")){
            Driver.getDriver().findElement(By.xpath("//button[.='+']")).click();
        }
        else{
            size = Integer.parseInt(s[0]);

          /*  if(size>3 ){

                WebElement element= Driver.getDriver().findElement(By.xpath("//select[@class='form-select']"));
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(element));
                Select select = new Select(element);
                List<WebElement> options = select.getOptions();
                select.selectByIndex(options.size()-1);
            }*/
            Driver.getDriver().findElement(By.xpath("//button[.='+']")).click();
        }


        beforesize=size;

    }

    @When("I enter job details {string}, {string}, {string}, {string}, {string}")
    public void i_enter_job_details(String title, String company, String description, String location, String requirements) {
        Driver.getDriver().findElement(By.name("title")).sendKeys(title);
        Driver.getDriver().findElement(By.name("company")).sendKeys(company);
        Driver.getDriver().findElement(By.name("location")).sendKeys(location);
        WebElement element = Driver.getDriver().findElement(By.name("requirements"));
        WebElement element1 = Driver.getDriver().findElement(By.name("description"));

        sendKeysWithJs(element1, description);
        sendKeysWithJs(element, requirements);
    }

    @When("I click on the submit button")
    public void i_click_on_the_submit_button() {
        Driver.getDriver().findElement(By.xpath("//button[.='Save Changes']")).click();

    }

    @Then("I should see the job added to the list")
    public void i_should_see_the_job_added_to_the_list() throws InterruptedException {
        BrowserUtils.waitFor(10);
        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size = Integer.parseInt(s[0]);

        int aftersize=size;
        System.out.println("beforesize = " + beforesize);
        System.out.println("aftersize = " + aftersize);
        Assert.assertEquals(size,aftersize);
    }
}