package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default");
    private By addToCartButton = By.xpath("//span[text()='Add to Cart']");
    private By successAlert = By.cssSelector(".alert-success");

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean addProductToCart(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(productName);
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

            // Verify success alert
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert)).isDisplayed();
        } catch (Exception e) {
            System.out.println("❌ Add to cart failed: " + e.getMessage());
            return false;
        }
    }
}
