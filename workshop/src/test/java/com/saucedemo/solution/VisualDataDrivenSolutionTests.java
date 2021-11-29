package com.saucedemo.solution;

import com.saucedemo.solution.pages.LoginPage;
import com.saucedemo.solution.pages.ProductsPage;
import com.saucedemo.solution.pages.ShoppingCartPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class VisualDataDrivenSolutionTests extends AbstractTestBase {
    //declare a bunch of variables
    private RemoteWebDriver driver;

    /*
     * Configure our data driven parameters
     * */
    @Parameterized.Parameter
    public String browserName;
    @Parameterized.Parameter(1)
    public String platform;
    @Parameterized.Parameter(2)
    public String browserVersion;
    @Parameterized.Parameter(3)
    public String viewportSize;
    // resolutionName is an identifier of the browser resolution
    @Parameterized.Parameter(4)
    public String resolutionName;

    @Parameterized.Parameters()
    public static Collection<Object[]> crossBrowserData() {
        return Arrays.asList(new Object[][]{
                {"Chrome", "Windows 10", "latest", "1080x720", "1080p"},
                {"Safari", "macOS 10.15", "latest", "1080x720", "1080p"}
        });
    }

    @Before
    public void setUp() throws Exception {
        //setting our browser/os capabilities
        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability(CapabilityType.BROWSER_NAME, browserName);
        browserOptions.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        browserOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);

        //pass information to sauce labs
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", SAUCE_USERNAME);
        sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.setCapability("name", testName.getMethodName());
        sauceOptions.setCapability("build", buildName);
        browserOptions.setCapability("sauce:options", sauceOptions);

        //pass information to screener.io
        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", SCREENER_API_KEY);
        visualOptions.setCapability("projectName", "Sauce Demo Java");
        visualOptions.setCapability("viewportSize", viewportSize);
        visualOptions.setCapability("failOnNewStates", false);

        browserOptions.setCapability("sauce:visual", visualOptions);

        //point to Screener hub
        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
    }
    @Test()
    public void visualFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        //provide the test name
        driver.executeScript("/*@visual.init*/", resolutionName);
        //take a visual snapshot of our page
        loginPage.takeSnapshot();

        loginPage.login("standard_user");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.takeSnapshot();

        productsPage.addAnyProductToCart();
        ShoppingCartPage cart = new ShoppingCartPage(driver);
        cart.visit();
        cart.takeSnapshot();

        //we only need a single assertion per 'init'
        //get the results object and check to see if any visual discrepancies were found
        Map<String, Object> response = cart.getVisualResults();
        assertNull(response.get("message"));
    }
}
