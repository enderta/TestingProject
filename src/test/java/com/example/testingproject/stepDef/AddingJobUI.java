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

public class AddingJobUI {
    String title;
    String company;
    String description;
    String location;
    String requirements;
    List<WebElement> elements;
    int beforesize;
    @When("I click on the add job button")
    public void i_click_on_the_add_job_button() throws InterruptedException {
       Thread.sleep(2000);
       Driver.getDriver().navigate().refresh();

        Thread.sleep(5000);

        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size = Integer.parseInt(s[0]);

        if(size>3){
           WebElement element= Driver.getDriver().findElement(By.xpath("//select[@class='form-select']"));
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            select.selectByIndex(options.size()-1);
        }
        elements = Driver.getDriver().findElements(By.xpath("//div[@class='col-md-4']"));
        beforesize=elements.size();
        Driver.getDriver().findElement(By.xpath("//button[.='+']")).click();
    }
    @When("I enter job details {string}, {string}, {string}, {string},, {string}")
    public void i_enter_job_details(String title, String company, String description, String location, String requirements) {
        //| jobTitle | jobCompany | jobDescription | jobLocation | jobRequirements |
        this.title=title;
        this.company=company;
        this.description=description;
        this.location=location;
        this.requirements=requirements;

        Driver.getDriver().findElement(By.name("title")).sendKeys(title);
        Driver.getDriver().findElement(By.name("company")).sendKeys(company);

        Driver.getDriver().findElement(By.name("location")).sendKeys(location);
        WebElement element = Driver.getDriver().findElement(By.name("requirements"));
        WebElement element1 = Driver.getDriver().findElement(By.name("description"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        //send keys with javascript
        js.executeScript("arguments[0].value= arguments[1]", element1, description);
        JavascriptExecutor js1 = (JavascriptExecutor) Driver.getDriver();
        js1.executeScript("arguments[0].value= arguments[1]", element, requirements);


    }
    @When("I click on the submit button")
    public void i_click_on_the_submit_button() throws InterruptedException {
        Driver.getDriver().findElement(By.xpath("//button[.='Save Changes']")).click();
       Thread.sleep(2000);
        Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Job added successfully",text);
        alert.accept();

    }
    @Then("I should see the job added to the list")
    public void i_should_see_the_job_added_to_the_list() throws InterruptedException {




        Driver.getDriver().navigate().refresh();
        Thread.sleep(5000);
        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size = Integer.parseInt(s[0]);

        if(size>3){
            WebElement element= Driver.getDriver().findElement(By.xpath("//select[@class='form-select']"));
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            select.selectByIndex(options.size()-1);
        }
        List<WebElement> elements1 = Driver.getDriver().findElements(By.xpath("//div[@class='col-md-4']"));
        int aftersize=elements1.size();
        Assert.assertEquals(beforesize+1,aftersize);


    }
}
