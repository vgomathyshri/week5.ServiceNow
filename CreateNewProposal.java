package week5.day2.servicenow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class CreateNewProposal extends ServiceNowBase {
	
	@BeforeTest
	public void setExcelFlie() {
		excelName = "ServiceNow";
		sheetName = "Proposal";
	}
	
	@Test(dataProvider = "fetchdata")
	public void runProposal(String Description, String firstDropDown) throws InterruptedException {
				
		//Create instance in Shadow class to find element in shadow root dom
		Shadow dom = new Shadow(driver);
		//Thread.sleep(10000);
		
		dom.setImplicitWait(15);//this implicit wait is given for shadow element
		
		
		//Click All 
		dom.findElementByXPath("//div[contains(@class,'sn-polaris-tab can')]").click();
//		dom.findElementByXPath("//div[text()='All']").click();
		//Enter service proposal in filter
		dom.findElementByXPath("//input[@id='filter']").sendKeys("Proposal",Keys.ENTER);
		Thread.sleep(4000);
		dom.findElementByXPath("//mark[text()='Proposal']").click();
		Thread.sleep(3000);
		WebElement frame = dom.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		dom.findElementByXPath("//button[text()='New']").click();
		
		
		String proposalNo = driver.findElement(By.xpath("//input[@id='std_change_proposal.number']")).getAttribute("value");
		//Enter Template Description
		driver.findElement(By.xpath("//input[@id='std_change_proposal.short_description']")).sendKeys("Descrip");
		
		//Change Request Values mandatory fields
		driver.findElement(By.xpath("//span[text()='Change Request values']")).click();
		
		//Select first dropdown values
		WebElement firstSelect = driver.findElement(By.xpath("//table[@id='std_change_proposal.template_valuefilters_table']//table//table/tbody/tr/td[@id='field']/select"));
		Select dd = new Select(firstSelect);
		dd.selectByVisibleText(firstDropDown);

		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		//Verify the proposal created from Web Table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='std_change_proposal_table']/tbody/tr"));
		int size = rows.size();
		String text = driver.findElement(By.xpath("//table[@id='std_change_proposal_table']/tbody/tr["+ size +"]/td[3]/a")).getText();
		//Checking if the proposal numbers are same
		if(proposalNo.equals(text))
		{
			System.out.println("Proposal Creation verified");
		}
	
	
	
	
	}
	
}
