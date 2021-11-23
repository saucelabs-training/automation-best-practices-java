package com.saucedemo.solution.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Page Object representing shopping cart page.
 */
public class ShoppingCartPage extends AbstractBasePage {
    public ShoppingCartPage(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public String getPagePart() {
        return "cart.html";
    }

    public int getItemsCount() {
        WebElement itemCounter;
        try{
            itemCounter = driver.findElement(By.cssSelector(".shopping_cart_badge"));
            return Integer.parseInt(itemCounter.getText());
        }
        catch (NoSuchElementException e)
        {
            return 0;
        }
    }
}
