package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.LoginPage;

public class LoginTest extends Base {

    @Test(priority = 2)
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Use the email generated during registration
        String email = RegisterTest.registeredEmail;
        String password = "Test@123"; // must match Excel password

        boolean status = loginPage.login(email, password);

        if (status) {
            System.out.println("✅ Login successful!");
            test.pass("✅ Login successful for email: " + email);
        } else {
            System.out.println("❌ Login failed!");
            test.fail("❌ Login failed for email: " + email);
        }

        Assert.assertTrue(status, "❌ Login failed with valid credentials.");
    }
}
