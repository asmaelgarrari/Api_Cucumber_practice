package com.library.pages;

import com.library.utilities.BrowserUtil;
import com.library.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Books {

    public String findBook;

    public String findBook(String bookName){

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']"))); // Use the correct locator
            // Now use sendKeys safely
            BrowserUtil.sleep(1);
            searchBox.sendKeys("Challenge yourself");
        } catch (NoSuchElementException e) {
            System.out.println("The search box was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

       // searchBox.sendKeys(bookName+ Keys.ENTER);
        WebElement actualBookName = Driver.getDriver().findElement(By.xpath("//td[3]"));
        return bookName;

    }
    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBox;


}
