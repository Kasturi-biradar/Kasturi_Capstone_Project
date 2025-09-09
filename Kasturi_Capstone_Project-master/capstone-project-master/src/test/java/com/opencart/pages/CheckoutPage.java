package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators (update if your site differs)
    private By checkoutButton = By.xpath("//*[@id=\"top-links\"]/ul/li[5]/a");
//    private By billingContinueBtn = By.id("button-payment-address");
//    private By shippingContinueBtn = By.id("button-shipping-address");
//    private By shippingMethodContinueBtn = By.id("button-shipping-method");
//    private By paymentMethodContinueBtn = By.id("button-payment-method");
//    private By confirmOrderBtn = By.id("button-confirm");
//    private By successMessage = By.cssSelector("#content h1");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String checkout() {
        try {
            // Step 1: Checkout link
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();

//            // Step 2: Billing
//            wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
//            System.out.println("➡️ Billing step completed");
//
//            // Step 3: Shipping address
//            wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
//            System.out.println("➡️ Shipping address step completed");
//
//            // Step 4: Shipping method
//            wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueBtn)).click();
//            System.out.println("➡️ Shipping method step completed");
//
//            // Step 5: Payment method
//            wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
//            System.out.println("➡️ Payment method step completed");
//
//            // Step 6: Confirm order
//            wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
//            System.out.println("✅ Order confirmed");
//
//            // Step 7: Verify success
//            String message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
//            return message.contains("Your order has been placed");
        } catch (Exception e) {
            System.out.println("❌ Checkout failed: " + e.getMessage());
            return "failed";
        }
		return "clicked on checkout";
    }
}
