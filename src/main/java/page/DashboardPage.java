package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage {
	WebDriver driver;
	
	//Create parameterized constructor for dashboard Page to transfer the driver
	public DashboardPage(WebDriver driver) {
//		super(driver);
		this.driver = driver;
//		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH,using="//strong[contains(text(),'Dashboard')]") WebElement dashBoardHeaderText;
	@FindBy(how=How.CSS, using="a[href$='#customers']") WebElement customerMenuElement;
	@FindBy(how=How.CSS,using="a[title='Add Customer']") WebElement addCustomerMenuElement;
	
	public void validateDashboardPage(String expected) {
		validateElement(dashBoardHeaderText.getText(),expected,"Dashboard Page is not available!");
	}
	
	public void clickOnCustomerMenuButton() {
		customerMenuElement.click();
	}
	
	public void clickOnAddCustomerMenuButton() {
		addCustomerMenuElement.click();
	}
}
