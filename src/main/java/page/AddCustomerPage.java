package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class AddCustomerPage extends BasePage {
	WebDriver driver;
	String genratedFullName;
	
	public AddCustomerPage(WebDriver driver) {
//		super(driver);
		this.driver = driver;
//		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH,using="//strong[contains(text(),'New Customer')]") WebElement newCustomerHeaderElement;
	@FindBy(how=How.CSS,using="input[name='name']") WebElement fullNameElement;
	@FindBy(how=How.CSS,using="select[name='company_name']") WebElement companyElement;
	@FindBy(how=How.CSS,using="input[name='email']") WebElement emailElement;
	@FindBy(how=How.CSS,using="input[name='phone']") WebElement phoneElement;
	@FindBy(how=How.CSS,using="input[name='address']") WebElement addressElement;
	@FindBy(how=How.CSS,using="input[name='city']") WebElement cityElement;
	@FindBy(how=How.CSS,using="input[name='port']") WebElement zipElement;
	@FindBy(how=How.XPATH,using="//p[contains(text(),'less than 5 digits')]") WebElement zipMinElement;
	@FindBy(how=How.XPATH,using="//p[contains(text(),'more than 9 digits')]") WebElement zipMaxElement;
	@FindBy(how=How.CSS,using="select[name='country']") WebElement countryElement;
	@FindBy(how=How.CSS,using="select[name='customer_group']") WebElement groupElement;
	@FindBy(how=How.CSS,using="button[id='save_btn']") WebElement saveElement;
	@FindBy(how=How.XPATH,using="//tbody/tr") List<WebElement> listOfElement;
	
	
	public void validateNewCustomerHeaderText(String expected) {
		validateElement(newCustomerHeaderElement.getText(),expected,"Customer Page is not avalable!");
	}
	
	public void insertFullName(String fullName) {
		genratedFullName =fullName+generateRandomNum(999);
		fullNameElement.sendKeys(genratedFullName);
	}
	
	public void clickOnFullName() {
		fullNameElement.click();
	}
	
	public void selectCompanyFromDropdown(String company) {
		selectDropdown(companyElement,company);
	}
	
	public void insertEmail(String email) {
		String generatedEmail = generateRandomNum(999)+email;
		emailElement.sendKeys(generatedEmail);
	}
	
	public void insertPhone(String phone) {
		String generatedPhone = phone + generateRandomNum(999);
		phoneElement.sendKeys(generatedPhone);
	}
	
	public void insertAddress(String address) {
		addressElement.sendKeys(address);
	}
	
	public void insertCity(String city) {
		cityElement.sendKeys(city);
	}
	
	public void zipCode(String zip) {
		zipElement.sendKeys(zip);
	}
	
	public void clearZipCode() {
		zipElement.clear();
	}
	
	public void zipCodeMinValidation(String zipcodeMin, String expZipLessCharText  ) {
		zipCode(zipcodeMin);
		clickOnSaveButton();
		validateElement(zipMinElement.getText(),expZipLessCharText,"Msg don't match");
	}
	
	public void zipCodeMaxValidation(String zipcodeMax, String expZipMoreCharText) {
		clickOnFullName();
		clearZipCode();
		zipCode(zipcodeMax);
		clickOnSaveButton();
		validateElement(zipMaxElement.getText(),expZipMoreCharText,"Msg don't match");
	}
	
	public void selectCountryFromDropDown(String country) {
		selectDropdown(countryElement, country);
	}
	
	public void selectGroup(String group) {
		selectDropdown(groupElement, group);
	}
	
	public void clickOnSaveButton() {
		saveElement.click();
	}
	//tbody/tr[1]/td[2]
	//tbody/tr[1]/td[9]/button
	
	String beforeXpath="//tbody/tr[";
	String afterXpath = "]/td[2]";
	String afterXpathDelete="]/td[9]/button";
	
	public void validateInsertedNameAndDelete() {
		int size =listOfElement.size();
		for(int i=1;i<size;i++) {
			String actualName = driver.findElement(By.xpath(beforeXpath+i+afterXpath)).getText();
			if(actualName.contains(genratedFullName)) {
				System.out.println("name exist!");
				driver.findElement(By.xpath(beforeXpath+i+afterXpathDelete)).click();
				driver.findElement(By.xpath("//a[@id='yesBtn']")).click();
			}
			break;
		}
	}
	
}
