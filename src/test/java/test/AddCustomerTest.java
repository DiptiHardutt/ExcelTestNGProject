package test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import testComponents.MyBaseTest;
import util.ExcelReader;

public class AddCustomerTest extends MyBaseTest {

	
	
	ExcelReader excelReader = new ExcelReader("src\\main\\java\\testData\\TestData.xlsx");
	//Mock Data/Test Data
	String userName = excelReader.getCellData("LoginInfo", "userName", 2);
	String password = excelReader.getCellData("LoginInfo", "password", 2);
	String expDashboardHeaderText = excelReader.getCellData("DashboardInfo", "DashBoardHeader", 2);
	String expNewCustomerHeaderText = excelReader.getCellData("AddCustomerInfo", "NewCustomerHeaderText", 2);
	String fullName = excelReader.getCellData("AddCustomerInfo", "FullName"	, 2);
	String companyName = excelReader.getCellData("AddCustomerInfo", "CompanyName"	, 2);
	String email = excelReader.getCellData("AddCustomerInfo", "Email"	, 2);
	String phone = excelReader.getCellData("AddCustomerInfo", "Phone"	, 2);
	String address = excelReader.getCellData("AddCustomerInfo", "Address"	, 2);
	String city = excelReader.getCellData("AddCustomerInfo", "City"	, 2);
	String zipCode = excelReader.getCellData("AddCustomerInfo", "ZipCode"	, 2);
	String zipCodeMin = excelReader.getCellData("AddCustomerInfo", "zipCodeMin"	, 2);
	String zipCodeMax = excelReader.getCellData("AddCustomerInfo", "zipCodeMax"	, 2);
	String expZipCodeMinText = excelReader.getCellData("AddCustomerInfo", "expZipCodeMinText"	, 2);
	String expZipCodeMaxText = excelReader.getCellData("AddCustomerInfo", "expZipCodeMaxText"	, 2);
	String country = excelReader.getCellData("AddCustomerInfo", "Country"	, 2);
	String group = excelReader.getCellData("AddCustomerInfo", "Group"	, 2);
	
	
	@Test
	public void userShouldBeAbleToAddCustomer() {
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickOnSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(expDashboardHeaderText);
		dashboardPage.clickOnCustomerMenuButton();
		dashboardPage.clickOnAddCustomerMenuButton();
		
		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		addCustomerPage.validateNewCustomerHeaderText(expNewCustomerHeaderText);
		addCustomerPage.insertFullName(fullName);
		addCustomerPage.selectCompanyFromDropdown(companyName);
		addCustomerPage.insertEmail(email);
		addCustomerPage.insertPhone(phone);
		addCustomerPage.insertAddress(address);
		addCustomerPage.insertCity(city);
		addCustomerPage.zipCodeMinValidation(zipCodeMin, expZipCodeMinText);
		addCustomerPage.zipCodeMaxValidation(zipCodeMax, expZipCodeMaxText);
		addCustomerPage.clickOnFullName();
		addCustomerPage.clearZipCode();
		addCustomerPage.zipCode(zipCode);
		addCustomerPage.selectCountryFromDropDown(country);
		addCustomerPage.selectGroup(group);
		addCustomerPage.clickOnSaveButton();
		addCustomerPage.validateInsertedNameAndDelete();
	}
}
