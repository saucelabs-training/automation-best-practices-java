package com.saucedemo.solution.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

/**
 * All page objects inherit from the base page.
 */
public abstract class AbstractBasePage {
    protected final RemoteWebDriver driver;

    public RemoteWebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5));
    }

    public AbstractBasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    /**
     * Executes a visual test.
     */
    public final void takeSnapshot() {
        //a JS command that Screener understands. The arg is the snapshot name
        driver.executeScript("/*@visual.snapshot*/", this.getClass().getSimpleName());
    }

    public void visit() {
        driver.get("https://www.saucedemo.com/" + getPagePart());
    }

    public abstract String getPagePart();

    /**
     * Screener uses this JavaScript to provide results of visual snapshot.
     *
     * @return Map of visual results
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getVisualResults() {
        return (Map<String, Object>) driver.executeScript("/*@visual.end*/");
    }
}
