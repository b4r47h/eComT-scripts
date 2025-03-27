package f1;

import java.awt.List;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class SweetShopTest2 {
	WebDriver d ;
	
	@BeforeClass
	public void su() {
		d = new ChromeDriver();
		d.get("https://sweetshop.vivrichards.co.uk");
		System.out.println("Navigated To Home Page");
	}
	@Test
	public void emptyBasket() throws Exception {
	    d.findElement(By.linkText("Browse Sweets")).click();
	    d.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div[2]/a")).click();
		WebElement basket = d.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[4]/a"));
		basket.click();
        WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));

        try {
          
            WebElement emptyBasketLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.linkText("Empty Basket")));
            emptyBasketLink.click();
            Alert a = d.switchTo().alert();	
            a.accept();

        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Timeout waiting for element to be clickable: " + e.getMessage());
        } finally {
            System.out.println("Emptied the basket");
        }
        WebElement fd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/div/div[1]/label")));
        Assert.assertTrue(fd.isDisplayed(), "FREE delivery option is not visible.");

		WebElement sd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[1]/div/div[2]/label")));
		Assert.assertTrue(sd.isDisplayed(),"Standard delivery not displayed");
		
		Assert.assertTrue(sd.isSelected(),"FREE delivery is selected");
		Assert.assertTrue(fd.isSelected(),"STANDARD delivery is selected");
		
		
	}

	@AfterClass
	public void End() {
		d.quit();
	}
}
