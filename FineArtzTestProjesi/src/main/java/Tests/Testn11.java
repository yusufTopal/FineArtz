package Tests;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Testn11 {
	
	
	WebDriver driver = null;
	String urunTitle=null;
	List<String> wishListTitles= new ArrayList<String>();
	
 	 @Given("^Start Driver and Browser$")
	   public void openBrowser() { 
		 
		  System.setProperty("webdriver.gecko.driver",  "/home/yusuf/Downloads/geckodriver");
	      driver = new FirefoxDriver(); 
	      driver.manage().window().maximize();
	      
	   } 
	   @When("^Navigate to n11.com$")
	    public void navigatetoURL() {
	        driver.get("https://www.n11.com/");
	    }
	 
	    @And("^Click log in$")
	    public void girisYapClick() {
	    	
	    	driver.findElement(By.className("btnSignIn")).click();
	      
	    }
	    @Then("^Log in page should appear$")
	    public void girisCheck()
	    {
	    	
	    	Assert.assertEquals(driver.getTitle(), "Giriş Yap - n11.com");
	    	
	    	 
	    }
	    @When("^Enter username$")
	    public void enterUserName() 
	    {
	    	driver.findElement(By.id("email")).sendKeys("yusuftopal00@hotmail.com");
	    }
	    
	    @And("^Enter password$")
	    public void enterPassword()
	    {	    		    	
	    	driver.findElement(By.id("password")).sendKeys("fineartz123");	      
	    }
	    
	    @And("^Log in click$")
	    public void uyeGirisYap() {
	    	
	    	driver.findElement(By.id("loginButton")).click();
	      
	    }
	    @Then("^Should logged in$")
	    public void loginCheck()
	    {
	        Assert.assertEquals(driver.getCurrentUrl(),"https://www.n11.com/");

	    	
	    }
	    @When("^Search text entered$")
	    public void enterSearchText() {
	    	
	    	driver.findElement(By.id("searchData")).sendKeys("Samsung");
	        
	    }
	    @And("^Search button clicked$")
	    public void aramaYapClick() {
	    	
	    	driver.findElement(By.className("searchBtn")).click();
	      
	    }
	    @Then("^Search results should appear$")
	    public void checkSearchResults()
	    {
	    	Assert.assertTrue(driver.findElements(By.className("notFoundContainer")).size()<=0); 	 
	    }
	    @When("^Search results appeared$")
	    public void lookForSecondPage() {
	    	
	    	List <WebElement> pageCount=driver.findElements(By.cssSelector(".pagination > a"));
	    	
	        Assert.assertTrue(pageCount.size()>3); 	
	    
	    	pageCount.get(1).click();
	    	
	    	   	
	    }
	    @And("^Check if there are more than three products$")
	    public void urunCheck()
	    {
	    	List <WebElement> productCount=driver.findElements(By.cssSelector("#view > ul>li"));
	    	Assert.assertTrue(productCount.size()>=3);
	    
	    }
	    @Then("^Add to favourites$")
	    public void favoriEkleClick() 
	    {
	    	
	    	driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[2]/span")).click();	
	    	urunTitle=driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/section/div[2]/ul/li[3]/div/div[1]/a")).getAttribute("title");
	       
	    }
	    @When("^The product added to wishlist$")
	    public void hesabımClick() {
	    	
	    	driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[2]/div[2]/div[1]/a[1]")).click();
	    }
	    @And("^My account page should appear$")
	    public void hesabimSayfasiCheck()
	    {
	    	Assert.assertTrue(driver.getTitle().equals("Siparişlerim - n11.com"));   	
	    }
	    @And("^Isteklerim click$")
	    public void isteklerimClick()
	    {
	     driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/ul/li[6]/a")).click();	
	  
	    }
	    @And("^Favorilerim click$")
	    public void favorilerimClick()
	    {
	     driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/ul/li[1]/div/a/h4")).click();	
	  
	    }
	    
	    @Then("^Isteklerim should appear$")
	    public void isteklerimSayfasiCheck()
	    {
	    	Assert.assertTrue(driver.getTitle().equals("Favorilerim - n11.com"));   	
	    }
	    
	    @When("^Wished device appear on the list$")
	    public void samsungCheck()
	    {
	    	
	    	List<WebElement> wishList = driver.findElements(By.cssSelector("#view > ul>li>div>div>a.plink"));
	   
	    	
	    
	    	for(WebElement we : wishList)
	    	{
	    		wishListTitles.add(we.getAttribute("title"));
	    		
	    	}
	    
	    	
	    	Assert.assertTrue(wishListTitles.contains(urunTitle));
	    	
	    
	    }
	    @And("^Remove the wished product$")	    
	    public void urunSilClick()
	    {
	    	int count=1;
	    	for(String s:wishListTitles)
	    	{
	    		if(s.contains(urunTitle))
	    		{
	    			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[3]/div[1]/div[2]/ul/li["+count+"]/div/div[3]/span")).click();

	    		}
	    		count++;
	    	}
	    	
	    		
	    	driver.findElement(By.xpath("/html/body/div[5]/div/div/span")).click();
	    }
	    @And("^Wished product should be removed from the list$")	    
	    public void urunSilCheck()
	    {
	    	driver.navigate().refresh();
	    	wishListTitles.clear();
	    	List<WebElement> wishList = driver.findElements(By.cssSelector("#view > ul>li>div>div>a.plink"));
	    	for(WebElement we : wishList)
	    	{
	    		wishListTitles.add(we.getAttribute("title"));

	    	}
	   
	    		Assert.assertTrue(!wishListTitles.contains(urunTitle));
	    		
	    }
	    	
	    @Then("^Log out$")	    
	    public void logOut()
	    {
	    	Actions builder = new Actions(driver);
	    	builder.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/header/div/div/div[2]/div[2]/div[2]/div[1]/a[1]"))).perform();
	    	
	    	driver.findElement(By.className("logoutBtn")).click();    	
	    	
	    	driver.quit();
	    	
	    }
	    
	    
	    
	    

}
