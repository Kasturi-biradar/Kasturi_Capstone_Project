package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By myAccountDropdown = By.xpath("//a[@title='My Account']");
    private By loginLink = By.linkText("Login");
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By accountHeader = By.xpath("//h2[text()='My Account']"); // appears after login

    /**
     * Performs login with given credentials.
     * Returns true if login successful, false otherwise.
     */
    public boolean login(String email, String password) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Open Login Page
            wait.until(ExpectedConditions.elementToBeClickable(myAccountDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();

            // Fill credentials
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);

            // Click login
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            // Wait for account header to confirm login
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountHeader));

            return driver.findElement(accountHeader).isDisplayed();

        } catch (Exception e) {
            System.out.println("❌ Login failed: " + e.getMessage());
            return false;
        }
    }
}
