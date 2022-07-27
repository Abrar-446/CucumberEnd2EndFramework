package stepdefinations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

/*Child Class*/
public class loginStepDefination extends BaseClass {
	//private static final Logger log=LogManager.getLogger(loginStepDefination.class);
	
	/*public WebDriver driver=null;
	public LoginPage loginPg;
	public AddNewCustomerPage addNewCustpg;
	public SearchCustomerPage searchCustpg;*/ // move all these pages to Base class
	//public Logger log;
	
	//this code will execute before every sceanrio
	//Lower order=1 value will execute first and Higher order value execute last in @Before annotation
	//@Before(order=1) //Ordering
	
	@Before("@Sanity") //Conditional hooks
	public void setup1() {
		log=LogManager.getLogger(loginStepDefination.class);
		//initialize logger
		//log = LoggerFactory.getLogger(loginStepDefination.class);
		//log=LoggerFactory.getLogger(loginStepDefination.class);
		//log=LogManager.getLogger("loginStepDefination");
//		log =Logger.getLogger("nopEcommerce");
//		PropertyConfigurator.configure("Log4j.properties");
		readConfig=new ReadConfig();
		System.out.println("Setup-1 Sanity method executed");
		
		String browser=readConfig.getBrowser();
		System.out.println(browser);
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		
		log.info("Setup-1 executed");
	}
	
	//We can also use multiple @Before annotations like below:
	//@Before(order=0)
	@Before("@Regression") //Conditional hooks
	public void setup2() {
		System.out.println("Setup-2 Regression method executed");
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		log.info("Setup-2 Executed........");
	}
	
	///////////Common methods for all tests//////////

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		/*WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();*/ // moved this piece of code to @Before
			
		loginPg= new LoginPage(driver);
		addNewCustpg=new AddNewCustomerPage(driver);
		searchCustpg=new SearchCustomerPage(driver);
		

		log.info("chrome browser launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		log.info("url opened");

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginPg.enterEmail(emailadd);
		loginPg.enterPassword(password);
		log.info("email address and password entered");

	}

	@When("Click on Login")
	public void click_on_login() {
		loginPg.clickOnLoginButton();

		log.info("Clicked on login button");

	}

	//////////Login feature///////////////////////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle=driver.getTitle();

		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");

			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test Failed: Login feature- page title not matched.");


		}


	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPg.clickOnLogOutButton();
		log.info("user clicked on logout link.");

	}

	/*
	 * @Then("close browser") public void close_browser() { driver.close();
	 * log.info("Browser closed");
	 * 
	 * //driver.quit(); }'
	 * This method will be moved to CloseStepdef.java
	 */
	
	
	///////////////////Add new Customer Feature//////////////

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actTitle=addNewCustpg.getPageTitle();
		String expTitle="Dashboard / nopCommerce administration";
	    if(actTitle.equals(expTitle)) {
	    	Assert.assertTrue(true);
	    	log.info("Test passed: user Can View Dashbaord passed");
	    }
	    else {
	    	Assert.assertTrue(false);
	    	log.warn("Test Failed: user Can View Dashbaord falied");
	    }
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustpg.clickOnCustomersMenu();
		log.info("User clicked on customers menu");
   
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addNewCustpg.clickOnCustomersMenuItem();
		log.info("User clicked on customers MENU ITEM");
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustpg.clickOnAddnew();
		log.info("User clicked on NEW button");
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actTitle=addNewCustpg.getPageTitle();
		String expTitle="Add a new customer / nopCommerce administration";
	    if(actTitle.equals(expTitle)) {
	    	Assert.assertTrue(true);
	    	log.info("User can view Add new customer page-Test passed");
	    }
	    else {
	    	Assert.assertTrue(false);
	    	log.warn("User can view Add new customer page-Test Failed");
	    }
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
	    //addNewCustpg.enterEmail("Test46new_5@gmail.com");
		addNewCustpg.enterEmail(generateEmailId()+ "@gmail.com"); //Random email address generator
	    addNewCustpg.enterPassword("Test@123");
	    addNewCustpg.enterFirstName("Abrar");
	    addNewCustpg.enterLastName("Ahmed");
	    addNewCustpg.enterGender("Male");
	    addNewCustpg.enterDob("7/22/2022");
	    addNewCustpg.enterCompanyName("Herbalife Nutrition");
	    addNewCustpg.enterAdminContent("TESTING");
	    addNewCustpg.enterManagerOfVendor("Vendor 1");   
	    log.info("User entered customer information");
	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustpg.clickOnSave();
		log.info("Clicked on Save Button");
	    
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedconfirmationmsg) {
	   
		String bodyTag=driver.findElement(By.tagName("Body")).getText();
		
		if(bodyTag.contains(expectedconfirmationmsg)) {
			Assert.assertTrue(true); //Pass
			log.info("User can view confirmation message-PASS");
	    }
	    else {
	    	Assert.assertTrue(false); //Fail
	    	log.info("User can view confirmation message-FAILED");
	    }
	}
	
	//////Search  customer feature starts///////////
	@When("Enter customer Email")
	public void enter_customer_email() {
		searchCustpg.enterEmailAddress("victoria_victoria@nopCommerce.com");
		log.info("Entered Email");
	}

	@When("Click on Search button")
	public void click_on_search_button() {
		searchCustpg.clickSearchBtn();
		log.info("Clicked on Search Button");
	    
	}

	@Then("user should found email in the search table")
	public void user_should_found_email_in_the_search_table() {
		String Expectedemail="victoria_victoria@nopCommerce.com";
		
		//1st Method
		/*
		  if(searchCustpg.searchCustomerByEmail(Expectedemail)==true) {
		  Assert.assertTrue(true); } else { Assert.assertTrue(false); }
		 */
		
		//2nd method
		Assert.assertTrue(searchCustpg.searchCustomerByEmail(Expectedemail));
		log.info("user found email in the search table-PASS");
	    
	}
	
////Search customer by Name
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
	    searchCustpg.enterFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	    searchCustpg.enterLastName("Terces");
	}
	
	
	@Then("user should found Name in the search table")
	public void user_should_found_Name_in_the_search_table() {
		String ExpectedName="Victoria Terces";
		
		//1st Method
		/*
		  if(searchCustpg.searchCustomerByName(ExpectedName)==true) {
		  Assert.assertTrue(true); } else { Assert.assertTrue(false); }
		 */
		
		//2nd method
		Assert.assertTrue(searchCustpg.searchCustomerByName(ExpectedName));
	    
	}

/*@After(order=2)
public void tearDown() {
	driver.quit();
	System.out.println("TearDown method executed");
}*/

//Failed scenarios capture screenshots
/*@After(order=2)
public void tearDown(Scenario sc) {
	System.out.println("TearDown method executed");
	
	if(sc.isFailed()==true) {
		//Capture screenshots for failed test cases
		//Convert web driver object to TakeScreenshot

		String fileWithPath = "C:\\Users\\abrar\\eclipse-workspace\\CucumberE2E\\Screenshot\\failedScreenshot.png";
		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination
		File DestFile=new File(fileWithPath);

		//Copy file at destination

		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Screenhsot Captured to screnshots folder");
	}
	driver.quit();
}*/

//We can also use multiple @After annotations like below:
//higher order=2 value will execute first and lower order value execute last in @After annotation

/*@After(order=1)
public void tearDown2() {
	driver.quit();
	System.out.println("TearDown2 method executed");
}*/

/*@BeforeStep
public void afterStepMethodDemo() {
	System.out.println("This is After step.......");
}

@AfterStep
public void beforeStepMethodDemo() {
	System.out.println("This is Before step.......");
}*/

@AfterStep
public void addScreenshot(Scenario scenario) {
	
	if(scenario.isFailed()) { //This statement needs to be removed in order to take screenshot for every step
	
		final byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		
		scenario.attach(screenshot, "image/png", scenario.getName());
	} 
}
}








