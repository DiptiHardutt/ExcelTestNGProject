package test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import testComponents.MyBaseTest;
import testComponents.Retry;
import util.ExcelReader;

public class LoginTest extends MyBaseTest{

	
	//Create Object for excel Reader class
	ExcelReader excelReader = new ExcelReader("src\\main\\java\\testData\\TestData.xlsx");
		
	//Login Data
	String userName = excelReader.getCellData("LoginInfo", "userName", 2);
	String password = excelReader.getCellData("LoginInfo", "password", 2);
	String expDashboardHeaderText = excelReader.getCellData("DashboardInfo", "DashBoardHeader", 2);
	String expAlertUserNameText = excelReader.getCellData("LoginInfo", "alertUserNameText", 2);
	String expAlertPasswordText = excelReader.getCellData("LoginInfo", "alertPasswordText", 2);
	
	@Test(retryAnalyzer=Retry.class)
	public void validUserShouldBeAbleToLogin() {
		//we have to make init method static so we can call our method by class name directly
		//and we have to return Webdriver in init method so we can transfer our driver here by following:-
		

		//Create object for Login Page class to call the intractable methods and use PageFactory not new keyword
		//create parameterized constructor for LoginPage
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickOnSigninButton();
		
		//Create object for Dashboard Page class
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
//		dashboardPage.validateDashboardPage(expDashboardHeaderText);
		dashboardPage.validateDashboardPage("valid");
		
	}
	
	@Test
	public void validateLoginAlertMsg() {
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickOnSigninButton();
		loginPage.validateUserAlertMsg(expAlertUserNameText);
		loginPage.insertUserName(userName);
		loginPage.clickOnSigninButton();
		loginPage.validatePasswordAlertMsg(expAlertPasswordText);
		
		
	}
}
