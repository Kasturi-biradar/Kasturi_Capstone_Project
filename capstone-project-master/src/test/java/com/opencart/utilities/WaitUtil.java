package com.opencart.utilities;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class WaitUtil {

	/**
	 * Waits until the page's document.readyState is "complete".
	 * 
	 * @param driver  WebDriver instance
	 * @param timeout timeout in seconds
	 */
	public static void waitForPageLoad(WebDriver driver, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout))
		.until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * Wait for element to be visible
	 * 
	 * @param driver WebDriver
	 * @param locator By locator
	 * @param timeout timeout in seconds
	 * @return WebElement
	 */
	public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Wait for element to be clickable
	 * 
	 * @param driver WebDriver
	 * @param locator By locator
	 * @param timeout timeout in seconds
	 * @return WebElement
	 */
	public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Wait until element disappears (not visible)
	 * 
	 * @param driver WebDriver
	 * @param locator By locator
	 * @param timeout timeout in seconds
	 * @return true if disappeared
	 */
	public static boolean waitForElementInvisibility(WebDriver driver, By locator, int timeout) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeout))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
}
