package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
	
	WebDriver ldriver;
	
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//input[@id='SearchEmail']")
	WebElement emailAdd;
	
	@FindBy(xpath="//button[@id='search-customers']")
	WebElement searchBtn;
	
	@FindBy(xpath="//table[@role='grid']")
	WebElement searchResults;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody//tr")
	List<WebElement> tableRows;
	
	@FindBy(xpath="//table[@id='customers-grid']//tbody/tr[1]/td")
	List<WebElement> tableColumns;
	
	@FindBy(xpath="//input[@id='SearchFirstName']")
	WebElement searchFirstNametxt;
	
	@FindBy(xpath="//input[@id='SearchLastName']")
	WebElement searchLastNametxt;
	
	
	//Action methods
	public void enterEmailAddress(String email) {
		emailAdd.sendKeys(email);
	}
	
	public void enterFirstName(String name) {
		searchFirstNametxt.sendKeys(name);
	}
	
	public void enterLastName(String name) {
		searchLastNametxt.sendKeys(name);
	}
	
	
	
	public void clickSearchBtn() {
		searchBtn.click();	
	}
	
	public boolean searchCustomerByEmail(String Expectedemail) {
		boolean found =false;
		//Total number of Rows in a Grid --//table[@id='customers-grid']//tbody//tr
		//Total number of Columns---//table[@id='customers-grid']//tbody/tr[2]/td
		int TotalRows=tableRows.size();
		
		//Total no of coulumns
		//int TotalCoulumns=tableColumns.size();
		
		//Start the loop to iterate all rows of Grid
		
		for(int i=1;i<=TotalRows;i++) { //Start from 1 as the first row is header
			WebElement Email=ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+ "]/td[2]"));
			String actualEmailAdd=Email.getText();
			
			if(actualEmailAdd.equals(Expectedemail)) {
				found=true;
				break;
			}
		}
		return found;
		
	}
	
	public boolean searchCustomerByName(String ExpectedName) {
		boolean found =false;
		//Total number of Rows in a Grid --//table[@id='customers-grid']//tbody//tr
		//Total number of Columns---//table[@id='customers-grid']//tbody/tr[2]/td
		int TotalRows=tableRows.size();
		
		//Total no of coulumns
		//int TotalCoulumns=tableColumns.size();
		
		//Start the loop to iterate all rows of Grid
		
		for(int i=1;i<=TotalRows;i++) { //Start from 1 as the first row is header
			WebElement Name=ldriver.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+ "]/td[3]"));
			String actualName=Name.getText();
			
			if(actualName.equals(ExpectedName)) {
				found=true;
				break;
			}
		}
		return found;
		
	}
	
	
	
	
}
