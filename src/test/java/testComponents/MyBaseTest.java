package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import page.LoginPage;


public class MyBaseTest  {
	public WebDriver driver;
	LoginPage loginPage;
	static Properties prop;
	static String browser;
	static String environment;
	
	// You can read the file with the following:-
	// Buffered Reader /Input Stream /File Reader /Scanner
	public static void readConfig() {
		try {
			prop = new Properties();
			InputStream fi = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(fi);
			browser = prop.getProperty("browser");
			environment = prop.getProperty("url");
							
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public  WebDriver init() {
		readConfig();
		String nameBrowser=System.getProperty("browser")!= null?System.getProperty("browser"):prop.getProperty("browser");
		if(nameBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(nameBrowser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else if(nameBrowser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			System.out.println("please enter a valid browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(environment);
		return driver;
		
	}

	@BeforeMethod(alwaysRun=true)
	public void launchApplication() {
		driver=init();
		
		
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
			try {
				driver.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				driver.quit();
	}

	
	public String takeScreenshots(String testCaseName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		try {
			FileUtils.copyFile(src, trg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return the path where screenshot is saved
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
		
			//reading json to String
		String json = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//convert string to HashMap using jackson databind
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data=	
			mapper.readValue(json, new TypeReference<List<HashMap<String,String>>>(){
				
			});
		
		
			return data;
		
	}
	
	
}
