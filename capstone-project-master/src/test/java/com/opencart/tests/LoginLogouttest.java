package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.base.Base;
import com.opencart.pages.LoginPage;
import com.opencart.pages.LogoutPage;

public class LoginLogouttest extends Base {

    @Test
    public void testLoginAndLogout() {
        LoginPage loginPage = new LoginPage(driver);
        LogoutPage logoutPage = new LogoutPage(driver);

        // ✅ Login
        boolean loginStatus = loginPage.login("john@test.com", "Test@123");
        Assert.assertTrue(loginStatus, "❌ Login failed!");

        // ✅ Logout
        boolean logoutStatus = logoutPage.logout();
        Assert.assertTrue(logoutStatus, "❌ Logout failed!");
    }
}
