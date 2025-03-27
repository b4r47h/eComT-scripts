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

public class SweetShopTest7 {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SweetShopTest7.class.getName());

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
    }

    @Test(priority = 0)
    public void testTotalPriceCalculation() {
        driver.get("https://sweetshop.vivrichards.co.uk/sweets");
        driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div/div[2]/a")).click();
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        String p1 =driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/span")).getText();
        String p2 =driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[2]/span")).getText();
        WebElement totalElement = driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[3]/strong"));
        String t = totalElement.getText();
        float p11 = Float.parseFloat(p1.replaceAll("[^0-9.]", ""));
        float p22 = Float.parseFloat(p2.replaceAll("[^0-9.]", ""));
        float tt = Float.parseFloat(t.replaceAll("[^0-9.]", ""));

        Assert.assertEquals(p11 + p22, tt, 0.01f, "Total price calculation is incorrect(COLLECT).");
        
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[2]/label")).click();
        
        Assert.assertEquals(p11 + p22, tt, 0.01f, "Total price calculation is incorrect(Standard).");
        logger.info("Total price calculation test executed.");
    }

    @Test(priority = 1)
    public void testDeliveryOptions() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement collectOption = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/label"));
        WebElement shippingOption = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[2]/label"));
        Assert.assertTrue(collectOption.isDisplayed() && shippingOption.isDisplayed(), "Delivery options are missing.");
        logger.info("Delivery options test executed.");
    }
    

    @Test(priority = 2)
    public void testNameFieldValidation() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys("12345");
        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();
        String colour = nameField.getCssValue("border-color");
        Assert.assertEquals(colour, "rgb(40, 167, 69)", "Border color is not as expected (green).");
        logger.info("Checkout name field validation test executed.");
    }

    @Test(priority = 3)
    public void testZipFieldValidation() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement zipField = driver.findElement(By.id("zip"));
        zipField.sendKeys("abcd");
        String colour = zipField.getCssValue("border-color");
        Assert.assertEquals(colour, "rgb(40, 167, 69)", "ZIP field did not turn red for invalid input.");
        logger.info("ZIP field validation test executed.");
    }

    @Test(priority = 4)
    public void testPaymentCardNameValidation() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement cardNameField = driver.findElement(By.id("cc-name"));
        cardNameField.sendKeys("12345");
        String colour = cardNameField.getCssValue("border-color");
        Assert.assertEquals(colour, "rgb(40, 167, 69)", "Card Name field did not turn red for invalid input.");
        logger.info("Payment card name validation test executed.");
    }

    @Test(priority = 5)
    public void testPromoCodeFunctionality() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement promoField = driver.findElement(By.xpath("/html/body/div/div/div[1]/form/div[1]/input"));
        WebElement applyButton = driver.findElement(By.xpath("/html/body/div/div/div[1]/form/div[1]/div[2]/button"));
        promoField.sendKeys("INVALIDCODE");
        WebElement totalElement = driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[3]/strong"));
        String t = totalElement.getText();
        
        applyButton.click();
        
        String t1 = totalElement.getText();
        Assert.assertFalse(t.equals(t1), "Invalid promo code No price change.");
        logger.info("Promo code functionality test executed.");
    }

    @Test(priority = 6)
    public void testCheckoutButtonRedirect() {
        driver.get("https://sweetshop.vivrichards.co.uk/basket");
        WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
        checkoutButton.click();
        Assert.assertFalse(driver.getCurrentUrl().contains("basket"), "Checkout button did not redirect.");
        logger.info("Checkout button redirect test executed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
