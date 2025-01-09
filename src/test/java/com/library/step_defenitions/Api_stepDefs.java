package com.library.step_defenitions;

import com.library.pages.LoginPage;
import com.library.utilities.LibraryUtil;
import com.library.utilities.RandomDataUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Api_stepDefs {
    RequestSpecification givenPart = RestAssured.given();
    ValidatableResponse thenPart;
    Response response;
    JsonPath jsonPath;
    String expectedValue;
    Map<String, Object> randomDataMap = new HashMap<>();


    LoginPage loginPage = new LoginPage();

    @When("I logged in Library UI as {string}")
    public void i_logged_in_library_ui_as(String role) {
        loginPage.login(role);

    }

    @Given("I logged Library api as a {string}")
    public void i_logged_library_api_as_a(String username) {

        String token = LibraryUtil.getTokenByRole(username);
        givenPart.header("x-library-token", token);


    }

    @Given("Accept header is {string}")
    public void accept_header_is(String acceptHeader) {

        givenPart.accept(acceptHeader);

    }

    @When("I send GET request to {string} endpoint")
    public void i_send_get_request_to_endpoint(String endPoint) {

        response = givenPart.when().get(endPoint);
        thenPart = response.then();
        jsonPath = response.jsonPath();
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {

        thenPart.statusCode(expectedStatusCode);
    }

    @Then("Response Content type is {string}")
    public void response_content_type_is(String expectedContentType) {

        thenPart.contentType(expectedContentType);
    }

    @Then("{string} field should not be null")
    public void field_should_not_be_null(String path){

        String actualValue = jsonPath.getString(path);
        Assert.assertNotNull(actualValue);

    }

    @Given("Path param is {string}")
    public void path_param_is(String value) {
        this.expectedValue = value;
        givenPart.pathParam("id", value);
    }
    @Then("{string} field should be same with path param")
    public void field_should_be_same_with_path_param(String path) {
        String actualValue = jsonPath.getString(path);
        assertEquals(expectedValue, actualValue);
    }

    @Then("following fields should not be null")
    public void following_fields_should_not_be_null(List<String> fields) {
        for (String eachField : fields) {
            assertNotNull(jsonPath.getString(eachField));
        }
    }

    @Given("Request Content Type header is {string}")
    public void request_content_type_header_is(String contentType) {

        givenPart.header("Content-Type",contentType);

    }
    @Given("I create a random {string} as request body")
    public void i_create_a_random_as_request_body(String dataType){

        switch (dataType){
            case "book":
                randomDataMap = RandomDataUtil.createRandomBook();
                break;

            case "user":
                randomDataMap = RandomDataUtil.createRandomUser();
                break;

            default:
                throw new RuntimeException("Invalid Data type");

        }
        givenPart.formParams(randomDataMap);

    }
    @When("I send POST request to {string} endpoint")
    public void i_send_post_request_to_endpoint(String endPoint) {

        response =  givenPart.when().post(endPoint);
        jsonPath= response.jsonPath();
        thenPart= response.then();

    }
    @Then("the field value for {string} path should be equal to {string}")
    public void the_field_value_for_path_should_be_equal_to(String path, String expectedText) {

        String actualText = jsonPath.getString(path);
        assertEquals(actualText,expectedText);
        System.out.println("response Body"+response.getBody().asString());
    }

}
