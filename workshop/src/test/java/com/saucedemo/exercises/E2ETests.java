package com.saucedemo.exercises;

import com.saucedemo.solution.pages.*;
import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class E2ETests extends SauceBaseTest {
//    Here's the first test to get you started. Try to run it
    @Test()
    public void appRenders() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        assertTrue(loginPage.isDisplayed());
    }
    @Test()
    public void loginWorks() {
        LoginPage loginPage = new LoginPage(driver);
        /*
         * Add your code below this
         * */



        /*
         * ^^^^^^^^ AddYour code above this ^^^^^^^^^
         * */
        assertTrue(new ProductsPage(driver).isDisplayed());
    }
    @Test()
    public void userCanCheckout() {
        /*
         * Add your code below this
         * */



        /*
         * ^^^^^^^^ AddYour code above this ^^^^^^^^^
         * */
        assertTrue(new CheckoutCompletePage(driver).isDisplayed());
    }

    /*
    * Don't do or look at the test below until the atomic tests section
    * */
    @Test()
    @Ignore("Ignoring until atomic tests section")
    public void userCanCheckoutAtomic() {
        /*
        * Add your code below this
        * */

        /*
         * 1. First navigate to the LoginPage
         * */

        /*
        * 2. Removing UI Login
        * We already know that our user can successfully login with loginWorks()
        * hence, we don't need to waste time, web requests, or add flakiness
        *
        * Uncomment the code below to make this possible
        * */
//        driver.manage().deleteAllCookies();
//        ((JavascriptExecutor)driver).executeScript("localStorage.clear();");
//        Cookie loginCookie = new Cookie("session-username", "standard_user");
//        //try document.cookie="session-username=standard_user" in browser Console
//        driver.manage().addCookie(loginCookie);
        //PS. In production code you can Hide this behavior in an App object.
        // I put it here only for clarity
        //You can create an App.setState(AppState appStateObject)
        // or Browser.clearLocalStorage()

        /*
        * 3. Add item to cart without UI interactions
        *
        * We also don't care whether or not clicking a button will add an item to a cart
        * We can easily cover this risk with another test
        * Hence, let's simulate adding an item to a cart by updating localStorage
        *
        * Uncomment the code below
        * */
//        ShoppingCartPage cart = new ShoppingCartPage(driver);
//        //this won't be possible if you're not logged in
//        cart.visit();
//        ((JavascriptExecutor)driver).executeScript("localStorage.setItem(\"cart-contents\", \"[4]\")");
//        driver.navigate().refresh();
//        //checking that app is in correct state
//        assertEquals(1, cart.getItemsCount());

        /*
        * 4. Truly test the checkout flow
        * All the preconditions have been met
        * - User is logged in
        * - User has item in a cart
        * Does the checkout process work?
        *
        * Fill in the code, you've done this before
        * */


        /*
        * ^^^^^^^^ AddYour code above this ^^^^^^^^^
        * */
        assertTrue(new CheckoutCompletePage(driver).isDisplayed());
    }
}
