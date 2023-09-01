package com.example.testingproject.runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        glue = "src/test/java/com/example/testingproject/stepDef",
        features = "src/test/resources/features",
        dryRun = true,
        tags = "@google"
)
public class FailedTestRunner {
}