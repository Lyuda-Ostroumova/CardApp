package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardAppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldSendCardApp() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.cssSelector("form"));
        form.findElement(By.cssSelector("input[type='text']")).sendKeys("Пупкин Василий");
        form.findElement(By.cssSelector("input[type='tel']")).sendKeys("+79152225511");
        form.findElement(By.cssSelector(".checkbox")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector(".paragraph")).getText();

        assertEquals("Ваша заявка успешно отправлена!", text.trim());

    }
}
