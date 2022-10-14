package week5.day2.servicenow;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;


import io.github.sukgu.Shadow;

public class CreateNewCaller extends ServiceNowBase {
	
	@BeforeTest
	public void setExcelFlie() {
		excelName = "ServiceNow";
		sheetName = "Callers";
	}
	@Test(dataProvider = "fetchdata")
	public void runNewCaler(String firstName, String lastName, String phoneNo) throws InterruptedException {

		
		//Create instance in Shadow class to find element in shadow root dom
		Shadow dom = new Shadow(driver);
		//Thread.sleep(10000);
		
		dom.setImplicitWait(15);//this implicit wait is given for shadow element
		
		
		//Click All 
		dom.findElementByXPath("//div[contains(@class,'sn-polaris-tab can')]").click();
//		dom.findElementByXPath("//div[text()='All']").click();
		//Enter service proposal in filter
		dom.findElementByXPath("//input[@id='filter']").sendKeys("Callers",Keys.ENTER);
		Thread.sleep(4000);
		dom.findElementByXPath("//mark[text()='Callers']").click();
		//Go into the frame
		WebElement frame = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		//Click New
		driver.findElement(By.xpath("//button[text()='New']")).click();
		//Enter First Name
		driver.findElement(By.id("sys_user.first_name")).sendKeys(firstName);
		//Enter Last Name
		driver.findElement(By.id("sys_user.last_name")).sendKeys(lastName);
		//Enter Phone Number
		driver.findElement(By.id("sys_user.mobile_phone")).sendKeys(phoneNo);
		//Click Submit
		driver.findElement(By.xpath("//button[contains(@class,'form_action_button  action')]")).click();
		
		//Verify caller Created
				
		//Number of Rows in each page
//		List<WebElement> noOfRows = driver.findElements(By.xpath("//table[@id='sys_user_table']/tbody/tr"));
//		int rowSize = noOfRows.size();
//		
//		//Number of pages
//		String pages = driver.findElement(By.xpath("//table[@class='list_nav list_nav_bottom ']//tr/td[@class='text-align-right']//span[@id='listv2_71feba092f6211100636351ef699b69e_total_rows']")).getText();
//		
//		for (int i = 0; i <= rowSize; i++) {
//			driver.findElements(By.xpath("//table[@id='sys_user_table']/tbody/tr[" + i + "]/td[4]"));
//		}
		
		//Search first name to verify caller created
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(firstName,Keys.ENTER);
		String name = driver.findElement(By.xpath("//table[@id='sys_user_table']/tbody/tr/td[text()='"+firstName+"']")).getText();
		if(name.equals(firstName)) {
			System.out.println("Caller Created Successfully");
		}
		
		
	}

}
