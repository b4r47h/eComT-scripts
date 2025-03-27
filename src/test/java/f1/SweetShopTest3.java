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
import java.util.List;

public class SweetShopTest3 {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SweetShopTest3.class.getName());

    @BeforeClass
    public void setUp() {
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testSocialMediaLinks() {
        driver.get("https://sweetshop.vivrichards.co.uk/login");
        WebElement socialMediaLinkF = driver.findElement(By.xpath("/html/body/div/div/div/div/a[2]/img"));
        socialMediaLinkF.click();
        Assert.assertFalse(driver.getCurrentUrl().contains("login"), "Facebook link is not working");
        
        WebElement socialMediaLinkT = driver.findElement(By.xpath("/html/body/div/div/div/div/a[1]/img"));
        socialMediaLinkT.click();
        Assert.assertFalse(driver.getCurrentUrl().contains("login"), "X(formerly Twitter) link is not working");
        
        WebElement socialMediaLinkL = driver.findElement(By.xpath("/html/body/div/div/div/div/a[3]/img"));
        socialMediaLinkL.click();
        Assert.assertFalse(driver.getCurrentUrl().contains("login"), "LinkedIn link is not working");
        logger.info("Social media links test executed.");
    }

    @Test
    public void testCheckouFieldValidation() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement nameField = driver.findElement(By.xpath("//input[@id='name']"));
        nameField.sendKeys("12345");
        WebElement warningMessage = driver.findElement(By.xpath("//span[@id='name-error']"));
        Assert.assertTrue(warningMessage.isDisplayed(), "No validation message for numeric name entry.");
        
        WebElement zipField = driver.findElement(By.xpath("//input[@id='zip']"));
        zipField.sendKeys("abcd");
        WebElement warningMessage2 = driver.findElement(By.xpath("//span[@id='zip-error']"));
        Assert.assertTrue(warningMessage2.isDisplayed(), "No validation message for invalid zip code.");
        logger.info("Checkout field validation test executed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
