package com.example.testingproject.stepDef;



import com.example.testingproject.utilities.ConfigurationReader;
import com.example.testingproject.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;


public class Hooks {

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        //BrowserUtils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        //BrowserUtils.destroyConnection();

    }

    @Before("@ui")
    public void setUp() {
        // we put a logic that should apply to every scenario
       Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // for example: setting up driver, maximizing browser, setting up implicit wait
    }

    @Before("@api")
    public void setUpApi() throws InterruptedException {
     //restasured base uri
        RestAssured.baseURI = ConfigurationReader.getProperty("base_url");
        //wait for response

    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "screenshot");
        }

        Driver.getDriver().manage().deleteAllCookies();
        Driver.closeDriver();


    }
}