package com.opencart.pages;

import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By myAccountMenu = By.xpath("//span[text()='My Account']");
    private By registerLink = By.linkText("Register");
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By agreeCheckbox = By.name("agree");
    private By continueButton = By.cssSelector("input[value='Continue']");
    private By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToRegisterPage() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }

    public String registerUser(Map<String, String> data) {
        navigateToRegisterPage();

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput))
                .sendKeys(data.get("firstname"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput))
                .sendKeys(data.get("lastname"));

        // Create unique email
        String baseEmail = data.get("email");
        String uniqueEmail = baseEmail.split("@")[0] + System.currentTimeMillis() + "@test.com";
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(uniqueEmail);

        wait.until(ExpectedConditions.visibilityOfElementLocated(telephoneInput))
                .sendKeys(data.get("phone"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput))
                .sendKeys(data.get("password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput))
                .sendKeys(data.get("password"));

        wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return uniqueEmail;  // Return the email for login
    }
}
