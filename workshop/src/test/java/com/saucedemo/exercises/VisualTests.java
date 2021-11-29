package com.saucedemo.exercises;

import com.saucedemo.solution.AbstractTestBase;
import com.saucedemo.solution.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class VisualTests extends AbstractTestBase {
    //declare a bunch of variables
    private RemoteWebDriver driver;

    public String browserName = "chrome";
    public String browserVersion = "latest";
    public String platform = "Windows 10";
    public String viewportSize = "1080x720";

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
        /*
         * Add your code below this
         * */

        //1. uncomment the line below, it starts the test session in screener and takes a test
        //name as the arg
        //driver.executeScript("/*@visual.init*/", "1080p");

        //2. uncomment to take a visual snapshot of our page
        //look inside of the takeSnapshot() to see how it's implemented
        //loginPage.takeSnapshot();

        //3. Now we want to capture the snapshot of the products page
        // use the ProductsPage() to capture it's snapshot
        //loginPage.login("standard_user");
        //create new products page
        // takeSnapshot()

        //4. use the ProductsPage object to addAnyProductToCart();
        // then visit() to the ShoppingCartPage()
        // and then takeSnapshot() of the cart
        //productsPage.addAnyProductToCart();

        /*
         * ^^^^^^^^ AddYour code above this ^^^^^^^^^
         * */

        //5. Finally we perform an assertion and check for any visual differences
        //we only need a single assertion per 'init'
        //get the results object and check to see if any visual discrepancies were found
        // uncomment the code below, run your test, it should pass
//        Map<String, Object> response = cart.getVisualResults();
//        assertNull(response.get("message"));
    }
}
