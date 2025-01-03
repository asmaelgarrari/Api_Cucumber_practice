package com.library.step_defenitions;

import com.library.utilities.BrowserUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Smoke_stepDefs {

    @Given("I am on the login page")
    public void i_am_on_the_login_page(){

        System.out.println("----DONE IN HOOKS-----");
    }

    @Then("title should be {string}")
    public void title_should_be(String expectedTittle){
        BrowserUtil.sleep(1);
        BrowserUtil.verifyTitle(expectedTittle);

    }
}
