package week5.day2.servicenow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class CreateNewArticle extends ServiceNowBase {
	@BeforeTest
	public void setExcelFile() {
		excelName = "ServiceNow";
		sheetName = "Article";
	}
	
	
	@Test(dataProvider = "fetchdata")
	public void runNewArticle(String knowledge, String category1, String category2,String Description) throws InterruptedException {
		//Create instance in Shadow class to find element in shadow root dom
		Shadow dom = new Shadow(driver);
		//Thread.sleep(10000);
		
		dom.setImplicitWait(18);//this implicit wait is given for shadow element
		
		
		//Click All 
		dom.findElementByXPath("//div[contains(@class,'sn-polaris-tab can')]").click();
//		dom.findElementByXPath("//div[text()='All']").click();
		//Enter service proposal in filter
		dom.findElementByXPath("//input[@id='filter']").sendKeys("Know",Keys.ENTER);
		Thread.sleep(4000);
		dom.findElementByXPath("//mark[text()='Know']").click();
		//Go into the frame
		WebElement frame = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);

		//Click IT as knowledge base
		driver.findElement(By.xpath("//span[text()='"+ knowledge +"']")).click();
		//Click Create Article
		driver.findElement(By.xpath("//button[@type='button']")).click();
		//Select Knowledge Base
		driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_knowledge_base']")).click();
			//Switch to New Window 
			Set<String> windowhandles = driver.getWindowHandles();
			List<String> windows = new ArrayList<String>(windowhandles);
			driver.switchTo().window(windows.get(1));
			//Select IT
			driver.findElement(By.xpath("//table[@id='kb_knowledge_base_table']/tbody/tr/td/a")).click();
			//Switch back to main window
			driver.switchTo().window(windows.get(0));
		//Switch to frame again as we switch back to window	
		driver.switchTo().frame(frame);
		//Select Category
		WebElement categoryButton = driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_category']"));
		categoryButton.click();
		
		//Select category picker
		Thread.sleep(5000);
		//Click IT from category picker
		driver.findElement(By.xpath("//div[@id='kb_categories_dialog']/table/tbody/tr[contains(@style,'height: 100%')]/td//span[text()='" + category1 + "']")).click();
		driver.findElement(By.xpath("//div[@id='kb_categories_dialog']/table/tbody/tr[contains(@style,'height: 100%')]/td//span[text()='"+category2+"']")).click();
		//Click OK button
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		//Enter Short Description
		driver.findElement(By.id("kb_knowledge.short_description")).sendKeys(Description);
		//Click Submit
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
				
		
	}

}
