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

public class SweetShopTest4 {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SweetShopTest4.class.getName());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    @Test(priority = 1)
    public void testBrowseSweetsButton() {
        driver.get("https://sweetshop.vivrichards.co.uk");
        WebElement browseSweets = driver.findElement(By.xpath("//a[text()='Browse Sweets']"));
        browseSweets.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("sweets"), "Browse Sweets button did not work.");
        logger.info("Browse Sweets button test executed.");
    }

    @Test(priority = 4)
    public void testSaleBannerHyperlink() {
        driver.get("https://sweetshop.vivrichards.co.uk");
        WebElement saleBanner = driver.findElement(By.xpath("/html/body/div/header/div/img"));
        saleBanner.click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://sweetshop.vivrichards.co.uk"), "Sale banner did not redirect.");
        logger.info("Sale banner hyperlink test executed.");
    }

    @Test(priority = 5)
    public void testLoginWithInvalidCredentials() {
        driver.get("https://sweetshop.vivrichards.co.uk/login");
        driver.findElement(By.id("exampleInputEmail")).sendKeys("invalid@user.com");
        driver.findElement(By.id("exampleInputPassword")).sendKeys("wrongpass");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        Assert.assertFalse(driver.getCurrentUrl().equals("https://sweetshop.vivrichards.co.uk/00efc23d-b605-4f31-b97b-6bb276de447e.html"), "No error message displayed for incorrect login.");
        logger.info("Invalid login test executed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
