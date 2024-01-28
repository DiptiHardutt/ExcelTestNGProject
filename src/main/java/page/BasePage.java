package page;

import java.util.Random;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
////	protected WebDriver driver
//	protected WebDriver driver;
//	
//public BasePage(WebDriver driver) {
//		
//		this.driver = driver;
//		PageFactory.initElements(driver, this);
//		
//	}
	
	
	public int generateRandomNum(int num) {
		Random random = new Random();
		int genratedRandomNum=random.nextInt(num);
		return genratedRandomNum;
	}
	
	public void validateElement(String actual,String expected,String errorMessage) {
		Assert.assertEquals(actual, expected, errorMessage);
	}
	
	public void selectDropdown(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	

	
	
}
