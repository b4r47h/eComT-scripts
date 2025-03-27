package f1;

import org.openqa.selenium.Alert;
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

public class SweetShopTest6 {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SweetShopTest6.class.getName());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
    }

    @Test(priority = 0)
    public void testAddressFieldAcceptsAlphanumerics() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement addressField = driver.findElement(By.id("address"));
        addressField.sendKeys("123 Sweet Street");
        Assert.assertEquals(addressField.getAttribute("value"), "123 Sweet Street", "Address field did not accept alphanumeric input.");
        logger.info("Address field accepts alphanumerics test executed.");
        driver.get("https://sweetshop.vivrichards.co.uk");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/div[2]/a")).click();
       
    }

    @Test(priority = 1)
    public void testBasketHeadingVisibility() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement basketHeading = driver.findElement(By.xpath("/html/body/div/header/h1"));
        Assert.assertTrue(basketHeading.isDisplayed(), "Your Basket heading is not visible.");
        logger.info("Basket heading visibility test executed.");
        
    }


    @Test(priority = 3)
    public void testDeleteItemFromBasket() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        
        WebElement deleteButton = driver.findElement(By.linkText("Delete Item"));
        deleteButton.click();
        Alert a = driver.switchTo().alert();	
        a.accept();
        Assert.assertTrue(driver.findElements(By.className("my-0")).isEmpty(), "Item was not removed from the basket.");
        logger.info("Delete item from basket test executed.");
        driver.get("https://sweetshop.vivrichards.co.uk/sweets");
        driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div/div[2]/a")).click();
    }

    @Test(priority = 5)
    public void testVisiblePriceInBasket() {
        
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        String priceElement = driver.findElement(By.xpath("//*[@id=\"basketItems\"]/li[2]/strong")).getText();
        Assert.assertNotNull(priceElement, "Price is not visible in the basket.");
        logger.info("Visible price in basket test executed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
