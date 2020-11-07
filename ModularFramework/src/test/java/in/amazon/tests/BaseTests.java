package in.amazon.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import commonLibs.implementation.CommonDriver;
import in.amazon.pages.AmazonHomepage;

public class BaseTests {
	
	CommonDriver cmnDriver;

	AmazonHomepage homepage;
	
	WebDriver driver;

	@BeforeMethod
	public void setup() throws Exception {

		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(90);

		cmnDriver.setElementDetectionTimeout(20);

		cmnDriver.navigateToUrl("https://amazon.in");
		
		driver = cmnDriver.getDriver();

		homepage = new AmazonHomepage(driver);

	}

	@AfterMethod
	public void cleanUp() throws Exception {

		cmnDriver.closeAllBrowsers();
	}

}
