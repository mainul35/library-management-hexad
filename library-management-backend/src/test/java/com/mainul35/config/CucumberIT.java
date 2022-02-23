package com.mainul35.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, tags = "", features = "src/test/resources/features", glue = "com.mainul35.glue" )
public class CucumberIT {

}
