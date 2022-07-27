package stepdefinations;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.*;


import org.openqa.selenium.WebDriver;


import pageObject.AddNewCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;
import utilities.ReadConfig;

/*Parent Class*/
public class BaseClass {

	//Commonly used Methods and variables placed in Base Class
	
	
	public static WebDriver driver;
	public LoginPage loginPg;
	public AddNewCustomerPage addNewCustpg;
	public SearchCustomerPage searchCustpg;
	//public static Logger log;
	public static ReadConfig readConfig;
	public static  Logger log;
	
	public void setlog(Logger log) {
		this.log=log;
	}
	
	
	//To Generate Random Email Address
	public String generateEmailId()
	{
		String random=RandomStringUtils.randomAlphabetic(6);
		return random;
	}
}
