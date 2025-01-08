package com.library.step_defenitions;

import com.library.pages.LoginPage;
import com.library.utilities.LibraryUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Objects;

public class Api_stepDefs {
    RequestSpecification givenPart = RestAssured.given();
    ValidatableResponse thenPart;
    Response response;
    JsonPath jsonPath;


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

}
