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


    //Проверка заголовка страницы
    @Test
    public void testPageTitle() {
        String title = driver.getTitle();
        assertEquals("Maven Repository: Search/Browse/Explore", title);
    }

    //Проверка наличия меню навигации
    @Test
    public void testNavigationMenuPresence() {
        WebElement nav = driver.findElement(By.className("page"));
        assertTrue(nav.isEnabled());
    }

    //Проверка кнопки Search на главной странице
    @Test
    public void testRunTestsButton() {
        WebElement runButton = driver.findElement(By.id("search"));
        assertTrue(runButton.isDisplayed());

        runButton.click();
        WebElement resultsBlock = driver.findElement(By.id("search"));
        assertTrue(resultsBlock.isDisplayed());

        String currentUrl = driver.getCurrentUrl();
        assertFalse("URL не изменился после клика", currentUrl.contains("https://mvnrepository.com/search?q="));
    }

    //Проверка заполнения текстового поля
    @Test
    public void testFillTextField() {
        WebElement searchInput = driver.findElement(By.id("query"));
        // Проверяем, что поле отображается и доступно для ввода
        assertTrue("Поле поиска не отображается", searchInput.isDisplayed());
        assertTrue("Поле поиска недоступно для ввода", searchInput.isEnabled());

        // Вводим текст
        String testText = "selenium";
        searchInput.sendKeys(testText);

        // Проверяем, что текст успешно введен
        String enteredText = searchInput.getAttribute("value");
        assertEquals("Введенный текст не совпадает", testText, enteredText);
    }

    //Проверка перехода по ссылке нажатием на пункт меню Popular
    @Test
    public void NavigateToPopularTest() {
        WebElement popularLink = driver.findElement(By.linkText("Popular"));
        popularLink.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("URL после перехода не совпадает", "https://mvnrepository.com/popular", currentUrl);
    }


    //Проверка наличия на странице заголовка
    @Test
    public void testExamplesSectionContent() {
        WebElement header = driver.findElement(By.xpath("//h1[contains(text(), \"What's New in Maven\")]"));
        assertTrue(header.isDisplayed());
        WebElement n = driver.findElement(By.className("sidebar"));
        assertTrue(n.isEnabled());
    }

    //Проверка размеров картинки
    @Test
    public void testSizePicture(){
        WebElement picture = driver.findElement(By.className("im-logo"));
        int weigth = 48;
        int height = 48;
        assertEquals(picture.getSize().width,weigth);
        assertEquals(picture.getSize().height,height);
    }

    //Проверка footer
    @Test
    public void testFooterPresence() {
        WebElement footer = driver.findElement(By.tagName("footer"));
        assertTrue(footer.isDisplayed());
    }


    //Проверка ссылки на логотипе
    @Test
    public void testLinkInDiv(){
        WebElement divElement = driver.findElement(By.id("logo"));
        WebElement links = divElement.findElement(By.tagName("a"));
        links.getAttribute("href");
    }

    //Проверка перехода по ссылкам по url
    @Test
    public void testURL() throws InterruptedException {
        String startUrl = driver.getCurrentUrl();
        // Переходим на следующую страницу
        WebElement nextPageLink = driver.findElement(By.linkText("Popular Categories"));
        nextPageLink.click();

        // Ждем загрузки страницы
        Thread.sleep(1000);
        String nextUrl = "https://mvnrepository.com/open-source";
        assertEquals(driver.getCurrentUrl(),nextUrl);

        // Возвращаемся назад
        driver.navigate().back();
        Thread.sleep(1000);
        assertEquals(startUrl, driver.getCurrentUrl());
    }
}
