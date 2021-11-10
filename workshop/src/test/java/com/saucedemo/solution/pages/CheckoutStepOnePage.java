package com.saucedemo.solution.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Page Object for Checkout Step One.
 */
public class CheckoutStepOnePage extends AbstractBasePage {
    public CheckoutStepOnePage(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getPagePart() {
        return "checkout-step-one.html";
    }

    public void enterPersonalDetails() {
        getDriver().findElement(By.cssSelector("#first-name")).sendKeys("test");
        getDriver().findElement(By.cssSelector("#last-name")).sendKeys("user");
        getDriver().findElement(By.cssSelector("#postal-code")).sendKeys("12345");
        getDriver().findElement(By.cssSelector("#continue")).click();
    }
}
