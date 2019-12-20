package com.github.ryctabo.demo;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class HelloServletFunctionalTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        DriverManagerType chrome = DriverManagerType.CHROME;
        ChromeDriverManager.getInstance(chrome).setup();
    }

    @Before
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void sayHello() {
        driver.get("http://localhost:8080/gradle-demo-java-webapp");

        driver.findElement(By.id("say-hello-text-input"))
                .sendKeys("Dolly");
        driver.findElement(By.id("say-hello-button")).click();

        assertEquals("Hello Page", driver.getTitle());

        String textInH2Element = driver.findElement(By.tagName("h2")).getText();
        assertEquals("Hello, Dolly!", textInH2Element);
    }
}
