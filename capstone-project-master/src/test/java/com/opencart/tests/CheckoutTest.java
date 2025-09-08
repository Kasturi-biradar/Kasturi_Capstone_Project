package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.CheckoutPage;

public class CheckoutTest extends Base {

	@Test(priority = 3)
    public void testCheckoutProcess() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String status = checkoutPage.checkout();

        if (status != null) {
            System.out.println("✅ Checkout successful!");
            test.pass("✅ Checkout successful!");
        } else {
            System.out.println("❌ Checkout failed!");
            test.fail("❌ Checkout failed!");
        }

      //  Assert.assertTrue(status, "Checkout process failed.");
    }
}
