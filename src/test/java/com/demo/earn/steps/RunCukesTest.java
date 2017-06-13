package com.demo.earn.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/"}, format = {"json:target/cucumber.json","html:target/cucumber"}, glue ="com.demo.steps")

public class RunCukesTest
{

}