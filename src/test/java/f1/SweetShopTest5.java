package f1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.logging.Logger;
import java.time.Duration;

public class SweetShopTest5 {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SweetShopTest5.class.getName());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    @Test(priority = 0)
    public void testLoginFieldsPresence() {
        driver.get("https://sweetshop.vivrichards.co.uk/login");
        Assert.assertNotNull(driver.findElement(By.id("exampleInputEmail")), "Email field is missing.");
        Assert.assertNotNull(driver.findElement(By.id("exampleInputPassword")), "Password field is missing.");
        logger.info("Login fields presence test executed.");
    }


    @Test(priority = 2)
    public void testEmptyLoginFieldsWarning() throws InterruptedException {
        driver.get("https://sweetshop.vivrichards.co.uk/login");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Thread.sleep(2000);
        WebElement warningMessage = driver.findElement(By.className("invalid-feedback"));
        Assert.assertNotNull(warningMessage, "No warning message displayed for empty fields.");
        logger.info("Empty login fields warning test executed.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}