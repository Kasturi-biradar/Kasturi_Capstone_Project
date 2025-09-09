package com.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencart.base.Base;
import com.opencart.pages.AddToCartPage;

public class AddToCartTest extends Base {

	@Test(priority = 2)
    public void testAddProductToCart() {
        AddToCartPage cartPage = new AddToCartPage(driver);
        boolean status = cartPage.addProductToCart("iPhone");
        Assert.assertTrue(status, "❌ Add to cart failed for product: iPhone");
    }
}
