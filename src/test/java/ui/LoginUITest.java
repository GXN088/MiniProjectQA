package ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginUITest {
    @Test
    public void testLoginUI() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://the-internet.herokuapp.com/login");

            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            username.sendKeys("tomsmith");
            password.sendKeys("SuperSecretPassword!");
            loginButton.click();

            WebElement message = driver.findElement(By.id("flash"));
            assert message.getText().contains("You logged into a secure area!");
        } finally {
            driver.quit();
        }
    }
}