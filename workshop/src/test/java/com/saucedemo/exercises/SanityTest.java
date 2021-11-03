package com.saucedemo.exercises;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBaseTest;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.*;

public class SanityTest extends SauceBaseTest {
    @Test
    public void functionalWorks() {
        WebDriver driver = new SauceSession().start();
        assertNotNull("Register for your free sauce account https://saucelabs.com/sign-up", driver);
    }
    @Test
    public void visualWorks() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Windows 10");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.setCapability("accesskey", System.getenv("SAUCE_ACCESS_KEY"));
        capabilities.setCapability("sauce:options", sauceOptions);

        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", System.getenv("SCREENER_API_KEY"));
        visualOptions.setCapability("projectName", "java-sanity");
        visualOptions.setCapability("viewportSize", "1280x1024");
        visualOptions.setCapability("failOnNewStates", false);
        capabilities.setCapability("sauce:visual", visualOptions);

        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, capabilities);

        driver.get("https://saucedemo.com");

        driver.executeScript("/*@visual.init*/", "Simple visual test");
        driver.executeScript("/*@visual.snapshot*/", "Home");
        Map<String, Object> response = (Map<String, Object>) driver.executeScript("/*@visual.end*/");
        assertNull(response.get("message"));
    }
}
