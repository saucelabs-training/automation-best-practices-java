package com.saucedemo.exercises;

import com.saucedemo.solution.AbstractTestBase;
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
public class VisualDataDrivenTests extends AbstractTestBase {
    //declare a bunch of variables
    private RemoteWebDriver driver;

    /*
     * Configure our data driven parameters
     * Each field gets a @Parameterized.Parameter annotation with an index
     * The order of these indices corresponds to the order of the strings
     * in the crossBrowserData()
     * For example, "chrome" = public String browserName
     * Hence, any value in the 0th index position will go into browserName
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

    /*
    * This is our collection of data driven values.
    * For demonstration purposes the data is hardcoded in the class.
    * It can also be read from an external data source.
    *
    * For each row of data, a new test method will be created.
    * In this case, we have 2 rows of data meaning that visualFlow()
    * will run 2 times, using the data from each row.
    * If we had 10 rows, the visualFlow() would execute 10 times
    *
    * In visual testing it's very valuable to data-drive a test method
    * across many browser/os/resolution combinations as rendering bugs
    * are extremely common in responsive web apps.
    * However, it's also possible to data drive on something like languages
    * if you want to see each page rendered in a corresponding language
    * */
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
        /*
         * Add your code below this
         * */

        //1. Replace the hardcoded values with the correct fields
        MutableCapabilities browserOptions = new MutableCapabilities();
        browserOptions.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        browserOptions.setCapability(CapabilityType.BROWSER_VERSION, "oldest");
        browserOptions.setCapability(CapabilityType.PLATFORM_NAME, "Windows ME");

        //pass information to sauce labs
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", SAUCE_USERNAME);
        sauceOptions.setCapability("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.setCapability("name", testName.getMethodName());
        sauceOptions.setCapability("build", buildName);
        browserOptions.setCapability("sauce:options", sauceOptions);


        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", SCREENER_API_KEY);
        visualOptions.setCapability("projectName", "Sauce Demo Java");
        //2. also pass in the correct viewport size field instead of hardcoding
        visualOptions.setCapability("viewportSize", "1x1");
        visualOptions.setCapability("failOnNewStates", false);

        /*
         * ^^^^^^^^ AddYour code above this ^^^^^^^^^
         * */

        browserOptions.setCapability("sauce:visual", visualOptions);

        //point to Screener hub
        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, browserOptions);
    }
    @Test()
    public void visualFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        //3. We are dynamically setting test name from our crossBrowserData()
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
