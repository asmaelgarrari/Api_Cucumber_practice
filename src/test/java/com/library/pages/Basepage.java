package com.library.pages;

import com.library.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Basepage {

    {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Books']")
    public WebElement books;

    @FindBy(xpath = "//a[contains(.,'Library')]")
    public WebElement dashBoard;


    @FindBy(xpath = "//a[@id='navbarDropdown']")
    public WebElement userName;


}
