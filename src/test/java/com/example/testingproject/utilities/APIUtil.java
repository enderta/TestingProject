package com.example.testingproject.utilities;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtil {
    public static String tkn(){
        Map<String,String> bdy=new HashMap<>();
        bdy.put("username",ConfigurationReader.getProperty("username"));
        bdy.put("password",ConfigurationReader.getProperty("password"));
        return given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(bdy)
                .when().post(ConfigurationReader.getProperty("post_login")).prettyPeek().jsonPath().getString("token");
    }
}
