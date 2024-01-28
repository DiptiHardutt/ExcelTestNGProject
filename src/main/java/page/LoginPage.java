package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
	 WebDriver driver;
	

	
	//Parameterized Constructor
	public LoginPage(WebDriver driver){
//		super(driver);
		this.driver = driver;
//		PageFactory.initElements(driver, this);
	}
	
	
	//WebElements/Attributes
	@FindBy(how=How.ID,using="user_name") WebElement userNameElement;
	@FindBy(how=How.ID,using="password") WebElement passwordElement;
	@FindBy(how=How.ID,using="login_submit") WebElement signinElement;
	
	//Intractable methods
	public void insertUserName(String userName) {
		userNameElement.sendKeys(userName);
	}
	
	public void insertPassword(String password) {
		passwordElement.sendKeys(password);
	}
	
	public void clickOnSigninButton() {
		signinElement.click();
	}
	
	public void validateUserAlertMsg(String expected) {
		String actualUserAlertMsg=driver.switchTo().alert().getText();
		validateElement( actualUserAlertMsg, expected, "Alert msg is not avalible!");
		driver.switchTo().alert().accept();
	}
	
	public void validatePasswordAlertMsg(String expected) {
		String actualPasswordAlertMsg=driver.switchTo().alert().getText();
		validateElement(actualPasswordAlertMsg, expected, "Alert msg is not available!");
		driver.switchTo().alert().accept();
	}
	
}
