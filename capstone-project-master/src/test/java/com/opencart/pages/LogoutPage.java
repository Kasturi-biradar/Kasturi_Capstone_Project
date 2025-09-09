package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LogoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By myAccountDropdown = By.xpath("//a[@title='My Account']");
    private By logoutLink = By.linkText("Logout");
    private By logoutConfirmationMessage = By.xpath("//h1[text()='Account Logout']");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean logout() {
        try {
            WebElement accountMenu = wait.until(ExpectedConditions.elementToBeClickable(myAccountDropdown));
            accountMenu.click();

            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
            logout.click();

            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutConfirmationMessage))
                       .getText().contains("Account Logout");

        } catch (Exception e) {
            System.out.println("❌ Logout failed: " + e.getMessage());
            return false;
        }
    }
}
