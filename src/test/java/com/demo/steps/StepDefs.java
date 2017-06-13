package com.demo.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import demo.policy.RestResponse;
import demo.policy.RestUtils;
import demo.policy.SharedStepState;
import demo.policy.SharedStepStateSingleton;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by viren.sanoj.shah on 6/13/2017.
 */
public class StepDefs {


    private String jsonRequest;

    private static final String propfilePath = "request.properties";

    private  static final String HOST = "http://agilathon-app-my-first-sample-project.7e14.starter-us-west-2.openshiftapps.com";
    //private  static final String HOST = "http://localhost:8088";
    private static final String RESOURCE_POST = "/policy/addPolicy";
    private static final String RESOURCE_DELETE = "/policy/deletePolicy/";
    private static final String RESOURCE_GET = "/policy/retrieve/";
    private SharedStepState stepState = SharedStepStateSingleton.getInstance();
    private RestResponse restResponse = null;




    private String  policyNo;


    @Given("^A Valid policy Holder enrolls (.*)$")
    public void a_Valid_policy_Holder_enrolls(String policyNo) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.policyNo = policyNo;
        jsonRequest = getProperties(propfilePath, policyNo);
        stepState.setJsonRequest(jsonRequest);
        System.out.println(jsonRequest);
    }
    private String getProperties(String propfilePath, String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream(propfilePath);
        prop.load(input);
        return prop.getProperty(key);
    }

    @When("^details are received by policy details manager$")
    public void details_are_received_by_policy_details_manger() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        restResponse = RestUtils.post(stepState,HOST,RESOURCE_POST );
        stepState.setJsonResponse(restResponse.getResponseBody());
        stepState.setResponseCode(restResponse.getResponseCode());
    }

    @Then("^Success response code is received and details of policy holder are persisted in Database$")
    public void success_response_code_is_received_and_details_of_policy_holder_are_persisted_in_Database() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(200,stepState.getResponseCode());
        String GET_POLICY = RESOURCE_GET + policyNo;
        restResponse = RestUtils.get(stepState,HOST,GET_POLICY);
        Assert.assertEquals(200,restResponse.getResponseCode());
        System.out.println("getResponse : " + restResponse.getResponseBody());
        String response = restResponse.getResponseBody();
    }

    @Given("^A existing policy Holder in db$")
    public void a_existing_policy_holder_in_db() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        policyNo = "6";
        String GET_POLICY = RESOURCE_GET + policyNo;
        restResponse = RestUtils.get(stepState,HOST,GET_POLICY);
        Assert.assertEquals(200,restResponse.getResponseCode());
    }


    @When("^details are deleted by policy details manager$")
    public void eqrIsReceived() throws Exception {
        Map<String,String> header = new HashMap<String,String>();
        policyNo = "6";
        String DELETE_POLICY = RESOURCE_DELETE + policyNo;
        restResponse = RestUtils.delete(header,HOST+DELETE_POLICY);
        stepState.setJsonResponse(restResponse.getResponseBody());
        stepState.setResponseCode(restResponse.getResponseCode());
    }

    @Then("^details are deleted from db$")
    public void error_response_code_is_received_and_details_of_policy_holder_are_persisted_in_Database() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        policyNo = "6";
        String GET_POLICY = RESOURCE_GET + policyNo;
        restResponse = RestUtils.get(stepState,HOST,GET_POLICY);
        Assert.assertEquals(404,restResponse.getResponseCode());
    }



}
