package com.example.testingproject.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "pretty"
        },
        glue = {"com/example/testingproject/stepDef"},
        features = {"src/test/resources/features"},
        dryRun = false,
        tags = "@apply"
)
public class CukesRunner {
}