package stepdefinations;

import io.cucumber.java.en.Then;

public class CloseStepDef extends BaseClass{
	@Then("close browser")
	public void close_browser() {
		driver.close();
		log.info("Browser closed");

		//driver.quit();
	}

}