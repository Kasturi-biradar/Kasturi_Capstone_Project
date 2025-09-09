package com.opencart.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.RegisterPage;
import com.opencart.utilities.ExcelUtil;

public class RegisterTest extends Base {

    // Store the generated email globally so LoginTest can use it
    public static String registeredEmail;

    @Test(priority = 1)
    public void testRegisterUser() {
        // Read row 1 from Excel (0-based index)
        Map<String, String> data = ExcelUtil.getRegisterData(1);

        RegisterPage registerPage = new RegisterPage(driver);

        // Register user and get the actual email used
        registeredEmail = registerPage.registerUser(data);

        if (registeredEmail != null && !registeredEmail.isEmpty()) {
            test.pass("🎉 Registration successful for email: " + registeredEmail);
            System.out.println("🎉 Registration successful for email: " + registeredEmail);
        } else {
            test.fail("❌ Registration failed for email: " + data.get("email"));
            System.out.println("❌ Registration failed for email: " + data.get("email"));
        }

        Assert.assertTrue(registeredEmail != null && !registeredEmail.isEmpty(),
                "❌ User registration failed!");
    }
}
