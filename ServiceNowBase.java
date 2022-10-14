package week5.day2.servicenow;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;



public class ServiceNowBase {
	
	public ChromeDriver driver;
	public String excelName;
	public String sheetName;
	
	@Parameters({"url","userName","password"})
	@BeforeMethod
	public void serviceNow(String url,String userName, String password) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(url);
		driver.findElement(By.id("user_name")).sendKeys(userName);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();
		
				
	}
	
@DataProvider(name="fetchdata")	
public String[][] testdata() throws IOException{

	return ReadExcelData.readSheetData(excelName, sheetName);
}
	
}

