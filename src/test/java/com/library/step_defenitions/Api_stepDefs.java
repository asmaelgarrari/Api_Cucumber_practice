package com.library.step_defenitions;

import com.library.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Api_stepDefs {

    LoginPage loginPage = new LoginPage();

    @When("I logged in Library UI as {string}")
    public void i_logged_in_library_ui_as(String role) {
        loginPage.login(role);

    }

}
