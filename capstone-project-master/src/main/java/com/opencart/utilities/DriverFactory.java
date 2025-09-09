package com.opencart.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().driverVersion("139.0.7258.154").setup(); // match your Chrome version
        return new ChromeDriver();
    }
}
