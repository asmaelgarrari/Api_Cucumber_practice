package com.library.step_defenitions;

import com.library.pages.Basepage;
import com.library.pages.Books;
import com.library.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class CreateBook_stepDefs {

    RequestSpecification givenPart = RestAssured.given().log().all();
    Response response;
    JsonPath jsonPath;
    ValidatableResponse thenPart;
    Basepage basepage = new Basepage();
    Books books = new Books();



    @Given("I logged Library api as a {string}")
    public void i_logged_library_api_as_a(String role) {

        String token = LibraryUtil.getTokenByRole(role);
        givenPart.header("x-library-token",token);
    }

    @Given("Accept header is {string}")
    public void accept_header_is(String acceptHeader) {

        givenPart.accept(acceptHeader);


    }

    @Given("Request Content Type header is {string}")
    public void request_content_type_header_is(String contentType) {

        givenPart.contentType(contentType);
    }

    @Given("I create a random {string} as request body")
    public void i_create_a_random_as_request_body(String book) {

        givenPart.formParams(RandomDataUtil.createRandomBook());
    }

    @When("I send POST request to {string} endpoint")
    public void i_send_post_request_to_endpoint(String endpoint) {

        response= givenPart.when().post(endpoint);
        jsonPath = response.jsonPath();
        thenPart = response.then();

    }

    @Given("I navigate to {string} page")
    public void i_navigate_to_page(String page) {

        basepage.books.click();

    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {

        thenPart.statusCode(expectedStatusCode);
    }

    @Then("Response Content type is {string}")
    public void response_content_type_is(String expectedContentType) {

        thenPart.contentType(expectedContentType);
    }

    @Then("the field value for {string} path should be equal to {string}")
    public void the_field_value_for_path_should_be_equal_to(String value, String text) {

        String path = jsonPath.getString(value);
        assertEquals(path, text);
    }

    @Then("{string} field should not be null")
    public void field_should_not_be_null(String bookId) {

        Assert.assertNotNull(bookId);
    }

    @Then("UI, Database and API created book information must match")
    public void ui_database_and_api_created_book_information_must_match() {
        // ui part
        BrowserUtil.sleep(1);
        //System.out.println(expectedBookName);
        String expectedBookName = (String) RandomDataUtil.createRandomBook().get("name");
        String actualBookNameInUI = books.findBook(expectedBookName);
        assertEquals(expectedBookName, actualBookNameInUI);

        System.out.println("expected "+expectedBookName);
        System.out.println("actual "+ actualBookNameInUI);
        // DB part
        String bookId = jsonPath.getString("book_id");
        DB_Util.runQuery("Select name from books where id =" + bookId);

        String actualBookNameInDB = DB_Util.getFirstRowFirstColumn();
        assertEquals(expectedBookName, actualBookNameInDB);

    }

}
