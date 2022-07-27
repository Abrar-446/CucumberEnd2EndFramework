package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)// this is for Junit
@CucumberOptions(
		//To run multiple feature mention folder name, it will pick all the features files from folder
		//features= ".//Features/",
		features= {".//Features/LoginFeature.feature",".//Features/AddCustomer.feature"},
		//features="C:\\Users\\abrar\\eclipse-workspace\\CucumberE2E\\Features\\AddCustomer.feature",
		
		glue= "stepdefinations",
		//plugin= {"pretty","html:target/CucumberReport/reports.html"},
		//cucumber plugin
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		//plugin= {"pretty","html:target/CucumberReport/Report.html","json:target/CucumberReport/Report.json","junit:target/CucumberReport/Report.xml"},
		dryRun=false,
		//strict=true,
		monochrome=true,
		//tags= {"@SanityTest"} //execute only sanity Tests
		//tags= {"@End2EndTest or @RegressionTest"}// executes only the scenarios which are comes under sanity OR regression
		//tags= {"@End2EndTest","@RegressionTest"}//executes comes under both Regresion and E2E test
		//Negative specific cases
		//tags= ("not @End2EndTest") //This will ignore E2E scenarios all other scenarios gets executed
		
		//CODE STUDIO TAGS BELOW
		tags= "@Sanity"
		//tags="@Regression or @Sanity" //will run scenarios tagged with Regression or sanity
		//tags="@Regression and @Sanity" //will run scenarios tagged with Regression as well as sanity
		//tags=("@Sanity and not @Regression") //will run scenarios tagged with sanity but not Regression
		)
		
//Cucumber Notes: Earlier we were using in tags options: ~ replaced with not, {} replaced with (), Comma(,) replaced with |or|
public class Run extends AbstractTestNGCucumberTests {
	

}
