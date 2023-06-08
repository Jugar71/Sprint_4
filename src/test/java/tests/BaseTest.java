package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.After;
import org.junit.Before;

public abstract class BaseTest {
    protected static WebDriver driver;
    private static final String url = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void webDriver() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.get(url);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
