package Testscript;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pageclasses.Amazonehomepage;
import Pageclasses.amazoneproductdetailpage;
import Pageclasses.productlistingpage;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Amazontestcases extends BaseClass
{
	WebDriver driver;
	Amazonehomepage homepage;
	productlistingpage listingpage;
	amazoneproductdetailpage detailPage;
	@BeforeMethod
	@Parameters({"browser"})
    public void browserIntialization(String browsername) throws Exception
    {
    	driver = initializemethod(browsername);
    	driver.get("https://selenium.qabible.in/");
    	System.out.println("Before method");
    	homepage=new Amazonehomepage(driver);
    	listingpage=new productlistingpage(driver);
    	detailPage=new amazoneproductdetailpage(driver);
    }
	@Test(priority =2 )
	public void TC01()
	{
		driver.findElement(By.name("q")).sendKeys("iphone"); //ID locator
		driver.findElement(By.name("(//div[@class='flex flex-auto items-center relative']//child::button)[2]")).click();
		driver.findElement(By.name("field-keywords")).clear();  //to clear the content in the search box
		driver.findElement(By.name("field-keywords")).sendKeys("SmartTV"); // name locator
		driver.findElement(By.name("(//div[@class='flex flex-auto items-center relative']//child::button)[2]")).click();
		//driver.findElement(By.className("a-button-input")).click();//class locator
		//driver.findElement(By.tagName("input")).click();
		driver.findElement(By.linkText("Conditions of Use")).click();//link text locator
		driver.findElement(By.partialLinkText("Privacy ")).click();//partial locator
		//tagname[attribute name='value']--syntax for CSS locator
		//input[class='a-button-input']--Eg for CSS locator
	}
	@Test(priority = 4)
	public void TC02() throws InterruptedException
	{
		driver.navigate().to("https://www.amazon.in/");
		//driver.navigate().back();
		//Thread.sleep(4000);
		//driver.navigate().forward();
		//Thread.sleep(4000);
		//driver.navigate().refresh();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebElement dropdown= driver.findElement(By.id("s-result-sort-select"));
		//Select obj= new Select(dropdown);
		//obj.selectByValue("price-asc-rank");	
		//obj.selectByVisibleText("Best Sellers");
		//obj.selectByIndex(0);
		List<WebElement> products=driver.findElements(By.xpath("//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']//child::span"));
		System.out.println(products.size());
		driver.quit();// used to close the browser
	}
	@Test(priority = 1)
	public void TC03()
	{
		String var=driver.findElement(By.xpath("//div[@class='container']//p")).getText();
		System.out.println(var);
		String var1=driver.findElement(By.xpath("//div[@class='top-logo']//child::img")).getAttribute("src");
		System.out.println(var1);
		String var2=driver.findElement(By.xpath("//div[@class='top-logo']//child::img")).getTagName();
		System.out.println(var2);
		driver.close();
	}
	@Test(priority = 3)
	public void TC04() throws InterruptedException
	{
		SoftAssert softassert=new SoftAssert();// object for soft assert
		driver.navigate().to("https://selenium.qabible.in/javascript-alert.php");
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		//Thread.sleep(2000);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().dismiss();
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().sendKeys("resmi");
		driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='btn btn-warning']"))));
		boolean status=driver.findElement(By.xpath("//button[@class='btn btn-warning']")).isDisplayed();// soft assertion
		softassert.assertEquals(status, true);
		String p=driver.switchTo().alert().getText();
		System.out.println(p);
		softassert.assertAll();
		
	}
	@Test(priority = 4)
	public void TC05()
	{
		driver.navigate().to("https://selenium.qabible.in/drag-drop.php");
		Actions action=new Actions(driver);
		WebElement Source=driver.findElement(By.xpath("//div[@id='todrag']//child::span"));
		WebElement Destination=driver.findElement(By.xpath("//div[@id='mydropzone']"));
		//action.dragAndDrop(Source, Destination).build().perform();
		//action.moveToElement(driver.findElement(By.id("others"))).build().perform();
		//action.contextClick().build().perform();
		action.doubleClick(driver.findElement(By.id("others"))).build().perform();
		/*
		 * action.keyDown(Keys.ENTER).build(); action.keyUp(Keys.ENTER).build();
		 * action.build().perform();
		 */
		driver.navigate().to("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
		boolean buttonstatus= driver.findElement(By.id("nav-search-submit-button")).isDisplayed();
		Assert.assertEquals(buttonstatus, true);
		boolean buttonstatus1= driver.findElement(By.id("nav-search-submit-button")).isEnabled();
		Assert.assertEquals(buttonstatus1, true);
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("(//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal'])[1]")).click();
		String parent=driver.getWindowHandle();
		Set<String> tabs=driver.getWindowHandles();
		for(String actual:tabs)
		{
			if(!actual.equalsIgnoreCase(parent))
			{
				driver.switchTo().window(actual);//to switch the WebDriver's focus to a specific browser window or tab
				WebElement element=driver.findElement(By.xpath("(//input[@name='submit.add-to-cart'])[2]"));
				JavascriptExecutor js=(JavascriptExecutor)driver; //object creation
				js.executeScript("arguments[0].scrollIntoView();",element);//to scroll an element
				//element.submit();//to click an element
				js.executeScript("arguments[0].click();", element); // to click on a hidden element using JS
			}
			driver.switchTo().window(parent);
		}
		
	}
	@BeforeSuite
	public void beforesuit()
	{
		System.out.println("Before suit");
	}
	@AfterSuite
	public void aftersuit()
	{
		System.out.println("After suit");
	}
	@BeforeTest
	public void beforetest()
	{
		System.out.println("Before test");
	}
	@AfterTest
	public void aftertest()
	{
		System.out.println("After test");
	}
	@AfterMethod
	public void tearDown()
	{
		//driver.quit();
	System.out.println("After method");	
	}
	@BeforeClass
	public void beforeclass()
	{
		System.out.println("Before class");
	}
	@AfterClass
	public void afterclass()
	{
		System.out.println("After class");
	}
	//@BeforeGroups
	public void beforegroup()
	{
		System.out.println("Before group");
	}
	//@AfterGroups
	public void aftergroup()
	{
		System.out.println("After group");
	}
	@Test(priority  =1)

	public void TC06() throws InterruptedException

	{

			SoftAssert softassert=new SoftAssert();//object of Softassert

		driver.navigate().to("https://selenium.qabible.in/javascript-alert.php");

		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.alertIsPresent());

		driver.switchTo().alert().accept();

		driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();

		Thread.sleep(2000);

		driver.switchTo().alert().dismiss();

		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();

		driver.switchTo().alert().sendKeys("sreeja");

		driver.switchTo().alert().accept();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='btn btn-warning']"))));

		boolean status=driver.findElement(By.xpath("//button[@class='btn btn-warning']")).isDisplayed();//Soft assertion is uesd by creating object

		softassert.assertEquals(status, true);

		driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();

		String p=driver.switchTo().alert().getText();

		System.out.println(p);

		softassert.assertAll();

	}
	@Test
	public void TC07() throws InterruptedException
	{
		SoftAssert softassert=new SoftAssert();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
		driver.navigate().to("https://selenium.qabible.in/");
		driver.findElement(By.xpath("(//div[@id='collapsibleNavbar']//child::a)[2]")).click();
		
		driver.findElement(By.xpath("//input[@id='single-input-field']")).sendKeys("Hello world");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='single-input-field']"))));
		String status= driver.findElement(By.xpath("//input[@id='single-input-field']")).getText();
		
		driver.findElement(By.xpath("//button[@id='button-one']")).click();
		softassert.assertEquals(status, true);
		
		driver.findElement(By.xpath("//input[@id='value-a']")).sendKeys("5");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='value-a']"))));
		driver.findElement(By.xpath("//input[@id='value-b']")).sendKeys("3");
		
		driver.findElement(By.xpath("//button[@id='button-two']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='button-two']"))));
		String total=driver.findElement(By.id("message-two")).getText();

		softassert.assertEquals(total, "Total A + B : 8");
		js.executeScript("window.scrollBy(0,350)", "");
		
		
		
		
		softassert.assertAll();
		}
	@Test
	public void TC08() throws InterruptedException

	{

			SoftAssert softassert=new SoftAssert();//object of Softassert

		driver.navigate().to("https://selenium.qabible.in/simple-form-demo.php");

		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("Hi there");

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();//Soft assertion is used by creating object

		String  status=driver.findElement(By.xpath("//div[@id='message-one']")).getText();//Soft assertion is used by creating object

		

		

		driver.findElement(By.xpath("//input[@id='value-a']")).sendKeys("5");

		driver.findElement(By.xpath("//input[@id='value-b']")).sendKeys("3");

		WebElement element=driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]"));

		JavascriptExecutor js= (JavascriptExecutor)driver;

		js.executeScript("arguments[0].scrollIntoView();",element);

		

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click();//Soft assertion is used by creating object

		String total=driver.findElement(By.id("message-two")).getText();

		softassert.assertEquals(total,"Total A + B : 8");

		softassert.assertAll();

}
	@Test 
	
	public void TC09()
	{
		driver.navigate().to("https://www.amazon.in/");
		homepage.searchproduct("Iphone");
		listingpage.productname().click();
		
		String parent=driver.getWindowHandle();
		Set<String> tabs=driver.getWindowHandles();
		for(String actual:tabs)
		{
			if(!actual.equalsIgnoreCase(parent))
			{
				driver.switchTo().window(actual);//to switch the WebDriver's focus to a specific browser window or tab
				WebElement element=detailPage.addtocartbutton();
				JavascriptExecutor js=(JavascriptExecutor)driver; //object creation
				js.executeScript("arguments[0].scrollIntoView();",element);//to scroll an element
				//element.submit();//to click an element
				js.executeScript("arguments[0].click();", element); // to click on a hidden element using JS
			}
			driver.switchTo().window(parent);
		}
	}
	@Test(dataProvider= "testdatas")
	public void TC10(String A , String B)
	{
		driver.navigate().to("https://selenium.qabible.in/simple-form-demo.php");
		driver.findElement(By.xpath("//input[@id='value-a']")).sendKeys(A);

		driver.findElement(By.xpath("//input[@id='value-b']")).sendKeys(B);

		WebElement element=driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]"));

		JavascriptExecutor js= (JavascriptExecutor)driver;

		js.executeScript("arguments[0].scrollIntoView();",element);

		

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click();
	}
	  @DataProvider (name="testdatas")
		public Object[][] testDatafeed()
		{
			Object [][] testdata=new Object[2][2];
			testdata[0][0]="10";
			testdata[0][1]="12";
			testdata[1][0]="13";
			testdata[1][1]="14";
			return testdata;
		}
	}
	
		
	
	

