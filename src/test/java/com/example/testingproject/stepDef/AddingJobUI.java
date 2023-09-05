package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.BrowserUtils;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddingJobUI {
    private String title;
    private String company;
    private String description;
    private String location;
    private String requirements;
    private List<WebElement> elements;
    private int beforesize;


    public static void waitFor(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

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
        waitFor(10);


        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size = Integer.parseInt(s[0]);

        WebElement element= Driver.getDriver().findElement(By.xpath("//select[@class='form-select']"));
        selectByIndexWhenSizeGreaterThanThree(size, element);

        elements = Driver.getDriver().findElements(By.xpath("//div[@class='col-md-4']"));
        beforesize=elements.size();
        Driver.getDriver().findElement(By.xpath("//button[.='+']")).click();
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
        waitFor(10);
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Job added successfully",text);
        alert.accept();

    }

    @Then("I should see the job added to the list")
    public void i_should_see_the_job_added_to_the_list() {

        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();

        String[] s = text.split(" ");
        int size = Integer.parseInt(s[0]);

        WebElement element= Driver.getDriver().findElement(By.xpath("//select[@class='form-select']"));
        selectByIndexWhenSizeGreaterThanThree(size, element);
        waitFor(10);
        List<WebElement> elements1 = Driver.getDriver().findElements(By.xpath("//div[@class='col-md-4']"));
        int aftersize=elements1.size();

        Assert.assertEquals(size,aftersize);
    }
}