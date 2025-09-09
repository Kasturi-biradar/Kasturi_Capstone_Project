package com.opencart.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.LoginPage;
import com.opencart.pages.RegisterPage;

public class RegisterAndLoginTest extends Base {

    @Test(priority = 1)
    public void testRegisterAndLogin() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Test data
        Map<String, String> data = new HashMap<>();
        data.put("firstname", "John");
        data.put("lastname", "Doe");
        data.put("email", "johndoe@test.com"); // base email
        data.put("phone", "9876543210");
        data.put("password", "Test@123");

        // 1️⃣ Register new user
        String registeredEmail = registerPage.registerUser(data);
        System.out.println("🎉 Registered email: " + registeredEmail);

        // 2️⃣ Login using newly registered email
        boolean loginStatus = loginPage.login(registeredEmail, data.get("password"));

        if (loginStatus) {
            System.out.println("✅ Login successful for " + registeredEmail);
            test.pass("✅ Login successful for " + registeredEmail);
        } else {
            System.out.println("❌ Login failed for " + registeredEmail);
            test.fail("❌ Login failed for " + registeredEmail);
        }

        Assert.assertTrue(loginStatus, "❌ Login failed for newly registered user.");
    }
}
