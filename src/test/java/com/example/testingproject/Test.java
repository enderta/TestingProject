package com.example.testingproject;

import com.example.testingproject.utilities.Driver;

public class Test {
    @org.junit.Test
    public void test() {
        Driver.getDriver().get("https://www.google.com");
    }
}
