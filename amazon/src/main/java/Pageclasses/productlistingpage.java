package Pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productlistingpage {
	WebDriver driver;
	@FindBy(xpath="(//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal'])[1]")
			WebElement productname;
			
			
			
			
			
			
public productlistingpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}






public WebElement productname()
{
	return productname;
}
			
	

}
