package com.saucedemo.solution.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;

/**
 * Page Object representing Products page.
 */
public class ProductsPage extends AbstractBasePage {

    public ProductsPage(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getPagePart() {
        return "inventory.html";
    }

    public boolean isDisplayed() {
        //new Selenium 4 constructor of WebDriverWait()
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By userNameFieldLocator = By.id("inventory_container");
        return
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(userNameFieldLocator)).isDisplayed();
    }

    public void addAnyProductToCart() {
        By userNameFieldLocator = By.cssSelector("#add-to-cart-sauce-labs-backpack");
        getWait().until(
                ExpectedConditions.visibilityOfElementLocated(userNameFieldLocator)).click();
    }
}
