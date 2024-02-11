package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import testComponents.MyBaseTest;

public class LoginTestWithJson extends MyBaseTest{

	@Test(dataProvider="data")
	public void validUserShouldBeAbleToLogin(HashMap<String,String> input) {
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.insertUserName(input.get("UserName"));
		loginPage.insertPassword(input.get("Password"));
		loginPage.clickOnSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(input.get("ValidationTextLogin"));
	}
	
	@DataProvider(name="data")
	public Object[][] getData() throws IOException{
		List<HashMap<String,String>> data = 
				getJsonDataToMap("C:\\Users\\shavo\\Selenium_June23\\ExcelTestNGProject\\src\\main\\java\\testData\\TF_TestData.json");
	final int limit = data.size();
	Object[][] maps = new Object[limit][1];
	for(int i=0;i<limit;i++) {
		maps[i][0]= data.get(i);
		
	}
		return maps;
	}
	
//	Object[][] maps = new Object[][];
//	maps = data.get(0);
//	maps = data.get(1);
	
}
