package com.opencart.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.LoginPage;
import com.opencart.pages.LogoutPage;
import com.opencart.utilities.ExcelUtil;

public class LogoutTest extends Base {

    @Test(priority = 5)
    public void testLogout() {
        // Read Excel data (row index 1 = second row)
        Map<String, String> data = ExcelUtil.getRegisterData(1);

        String email = data.get("email");
        String password = data.get("password");

        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);

        // 🔑 First, login using LoginPage
        boolean loginStatus = loginPage.login(email, password);
        Assert.assertTrue(loginStatus, "❌ Login failed before logout test!");

        // 🚪 Now perform logout
        boolean logoutStatus = logoutPage.logout();
        Assert.assertTrue(logoutStatus, "❌ Logout failed.");

        if (logoutStatus) {
            System.out.println("✅ Logout successful!");
            test.pass("✅ Logout successful!");
        } else {
            System.out.println("❌ Logout failed!");
            test.fail("❌ Logout failed!");
        }
    }
}
