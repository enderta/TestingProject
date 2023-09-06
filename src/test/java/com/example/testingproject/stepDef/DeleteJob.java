package com.example.testingproject.stepDef;

import com.example.testingproject.utilities.BrowserUtils;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class DeleteJob {
   public int beforesize;
    @Given("I am on the jobs page and get the number of the jobs")
    public void i_am_on_the_jobs_page_and_get_the_number_of_the_jobs() {

        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        System.out.println(text);
        String[] s = text.split(" ");
        int size =0;
        if(text.equals("No job click + button to add job")){
            System.out.println("No job to delete");
        }
        else{
            size = Integer.parseInt(s[0]);

        }

         beforesize = size;
    }
    @When("I click on the delete button first for the first job")
    public void i_click_on_the_delete_button_first_for_the_first_job() {
        Driver.getDriver().navigate().refresh();
        BrowserUtils.waitFor(3);
        WebElement element = Driver.getDriver().findElement(By.xpath("//button[.='Delete']"));
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        /*Alert alert = Driver.getDriver().switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals("Job deleted successfully",text);
        alert.accept();*/
    }
    @When("The number of jobs should be decreased by one")
    public void the_number_of_jobs_should_be_decreased_by_one() {
        BrowserUtils.waitFor(2);
        String text = Driver.getDriver().findElement(By.xpath("//*[contains(text(),'job')]")).getText();
        int size =0;
        if(text.equals("No job click + button to add job")){
          Assert.assertEquals("No job click + button to add job",text);
        }
        else{
            String[] s = text.split(" ");
            size = Integer.parseInt(s[0]);
            Assert.assertEquals(beforesize-1,size);
        }

    }
}
