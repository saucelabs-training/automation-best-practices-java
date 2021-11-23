package com.saucedemo.solution.pages;

import com.saucedemo.solution.pages.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends AbstractBasePage {
    public CheckoutCompletePage(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getPagePart() {
        return "/checkout-complete.html";
    }

    public boolean isDisplayed() {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#back-to-products"))).
                isDisplayed();
    }
}
