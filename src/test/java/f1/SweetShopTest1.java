package f1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SweetShopTest1 {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://sweetshop.vivrichards.co.uk/");

    }
    
    @AfterMethod
    public void returnHome() {
    	driver.get("https://sweetshop.vivrichards.co.uk/");
    	driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testHomePageLoads() {
        String x;
		x = driver.getTitle();
		Assert.assertTrue(x.contains("Sweet Shop"), "Home page did not load correctly.");
		System.out.println("Title Returned :" + x);
        		
        		
    }

    @Test(priority = 2)
    public void testLogoVisibility() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/nav/div/a")).isDisplayed(), "Logo is not visible.");
    }

    @Test(priority = 3)
    public void testDiscountSalesBanner() {
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/header/div/img")).isDisplayed(), "Discount sales banner is not present.");
    }

    @Test(priority = 5)
    public void testMenuLinks() throws InterruptedException {
    	driver.findElement(By.linkText("Sweets")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("sweets"), "Did not navigate to Sweets page.");
        Thread.sleep(3000);
        driver.navigate().back(); 
        // Navigate back to home
        Thread.sleep(3000);  
        driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[2]/a")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/header/h1")).isDisplayed(), "Did not navigate to About page.");
        driver.navigate().back();
        
        driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[3]/a")).click();
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/header/h1")).isDisplayed(), "Did not navigate to Login page.");
        driver.navigate().back();
        
        driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[3]/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("basket"), "Did not navigate to Basket page.");
       
    }
    
    

    @Test(priority = 6)
    public void testBrowseSweetsButton() {
        driver.findElement(By.xpath("/html/body/div/header/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("sweets"), "Did not navigate to Browse Sweets page.");
    }

    @Test(priority = 7)
    public void testAllSweetsHaveImages() {
    	driver.findElement(By.xpath("/html/body/div/header/a")).click();
        int imageCount = driver.findElements(By.className("card-img-top")).size();
        Assert.assertTrue(imageCount > 16, "Not all sweets have images.");
    }

    @Test(priority = 8)
    public void testBasketFunctionality() {
    	WebElement basket = driver.findElement(By.xpath("/html/body/div/header/a"));
    	basket.click();
    	driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[4]/a")).click();
        Assert.assertTrue(driver.findElements(By.className("my-0")).size() > 0, "Added item is not present in the basket.");
        
        
    }

    @Test(priority = 9)
    public void testLogin() {
    	WebElement logIn = driver.findElement(By.xpath("//*[@id=\"navbarColor01\"]/ul/li[2]/a"));
    	logIn.click();
    	driver.findElement(By.id("exampleInputEmail")).sendKeys("test@user.com");
    	driver.findElement(By.id("exampleInputPassword")).sendKeys("qwerty");
    	driver.findElement(By.xpath("/html/body/div/div/div/form/button")).click();
    	Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/header/h1")).isDisplayed(), "Login Unsuccesfull");
    }
    private void selectorFunction(WebElement x,String y) {
    	
    	Select s = new Select(x);
    	s.selectByVisibleText(y);
    	}
    
    @Test(priority =11)
    public void testBasketInput() throws InterruptedException{
    	driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[3]/a")).click();
    	Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/header/h1")).isDisplayed(),"Basket Is'nt Open");
    	driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/div[1]/input")).sendKeys("Bharath");
    	driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[1]/div[2]/input")).sendKeys("Krishna");
    	driver.findElement(By.id("email")).sendKeys("bharath");
    	WebElement proceed = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button"));
    	
    	proceed.click();
    	
    	String tmp = "Please enter a valid email address for shipping updates.";
    	String temail = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/div")).getText();
    	Assert.assertEquals(temail, tmp, "Warning Message not displayed");
    	driver.findElement(By.id("email")).clear();;
    	driver.findElement(By.id("email")).sendKeys("bharath@gmail.com");
    	driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("Anjilipparambil,AHY,CHLA");
    	driver.findElement(By.xpath("//*[@id=\"address2\"]")).sendKeys("Tower1,South,CHLA");
    	
    	
    	
    	WebElement country = driver.findElement(By.xpath("//*[@id=\"country\"]"));
    	WebElement city = driver.findElement(By.xpath("//*[@id=\"city\"]"));
    	selectorFunction(country, "United Kingdom");
    	selectorFunction(city, "Bristol");
    	driver.findElement(By.id("zip")).sendKeys("45678");
    
    	//Payment
    	
    	driver.findElement(By.xpath("//*[@id=\"cc-name\"]")).sendKeys("Bharath Krishna");
    	driver.findElement(By.xpath("//*[@id=\"cc-number\"]")).sendKeys("98237834568901265");
    	driver.findElement(By.xpath("//*[@id=\"cc-expiration\"]")).sendKeys("12/10");
    	driver.findElement(By.id("cc-cvv")).sendKeys("110");
    	proceed.click();
    	Assert.assertFalse(driver.getCurrentUrl().contains("basket"), "Clicking the button doesn't redirect");
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
