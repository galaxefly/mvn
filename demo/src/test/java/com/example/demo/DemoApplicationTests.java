package com.example.demo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DemoApplicationTests {

    private WebDriver driver;
    private final String baseUrl = "https://mvnrepository.com/";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver-win64//chromedriver-win64//chromedriver.exe/");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mvnrepository.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
