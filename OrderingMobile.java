package week5.day2.servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class OrderingMobile extends ServiceNowBase {
	@BeforeTest
	public void setExcelFlie() {
		excelName = "ServiceNow";
		sheetName = "Order Mobile";
	}
	
	@Test(dataProvider = "fetchdata")
	public void runOrderMobile(String broken,String allowance,String colour,String storage) throws InterruptedException {
				//Create instance in Shadow class to find element in shadow root dom
				Shadow dom = new Shadow(driver);
				//Thread.sleep(10000);
				
				dom.setImplicitWait(15);//this implicit wait is given for shadow element
				
				//Click All 
				dom.findElementByXPath("//div[contains(@class,'sn-polaris-tab can')]").click();
//				dom.findElementByXPath("//div[text()='All']").click();
				//Enter service catalog in filter
				dom.findElementByXPath("//input[@id='filter']").sendKeys("Service Catalog",Keys.ENTER);
				//click Service Catalog
				Thread.sleep(5000);
				dom.findElementByXPath("//mark[text()='Service Catalog']").click();
				//Switch to frame
				WebElement frame = dom.findElementByXPath("//iframe[@id='gsft_main']");
				driver.switchTo().frame(frame);
				//Click on Mobile
				driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
				
				//Click on iphone
				driver.findElement(By.xpath("//strong[contains(text(),'Apple iPhone 13')]")).click();
				//is this replacement or broken
				driver.findElement(By.xpath("//label[text()='"+ broken +"']")).click();
				//Monthly data allowance
				WebElement ele = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));	
				Select dd = new Select(ele);
				dd.selectByVisibleText(allowance);
				//Choose Colour
				driver.findElement(By.xpath("//label[text()='"+ colour +"']")).click();
				//Choose Storage
				driver.findElement(By.xpath("//label[text()='"+ storage +"']")).click();
				//Click order button
				driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
				//Get Request number
				String text = driver.findElement(By.xpath("//a[@id='requesturl']/b")).getText();
				System.out.println(text);
				

	}

}
