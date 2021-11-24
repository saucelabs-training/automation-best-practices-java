package com.saucedemo.solution;

import com.saucedemo.solution.pages.LoginPage;
import com.saucedemo.solution.pages.ProductsPage;
import com.saucedemo.solution.pages.ShoppingCartPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class VisualSolutionTests extends AbstractTestBase {
    private RemoteWebDriver driver;

    public String browserName = "chrome";
    public String browserVersion = "latest";
    public String platform = "Windows 10";
    public String viewportSize = "1080x720";

    @Before
    public void setUp() throws Exception {
        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability(CapabilityType.BROWSER_NAME, browserName);
        browserOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        browserOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", SAUCE_USERNAME);
        sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.setCapability("name", testName.getMethodName());
        sauceOptions.setCapability("build", buildName);
        browserOptions.setCapability("sauce:options", sauceOptions);

        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", SCREENER_API_KEY);
        visualOptions.setCapability("projectName", "Sauce Demo Java");
        visualOptions.setCapability("viewportSize", viewportSize);
        visualOptions.setCapability("failOnNewStates", false);

        browserOptions.setCapability("sauce:visual", visualOptions);

        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
    }
    @Test()
    public void visualFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        driver.executeScript("/*@visual.init*/", "demo app");
        loginPage.takeSnapshot();

        loginPage.login("standard_user");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.takeSnapshot();

        productsPage.addAnyProductToCart();
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.visit();
        cart.takeSnapshot();

        Map<String, Object> response = cart.getVisualResults();
        assertNull(response.get("message"));
    }
}
