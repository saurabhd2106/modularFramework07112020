package in.amazon.demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonLibs.implementation.CommonDriver;
import designPattern.AmazonHomepagePOM;

public class DemoAmazonPOM {

	CommonDriver cmnDriver;

	AmazonHomepagePOM homepage;

	@BeforeMethod
	public void invokeBrowser() throws Exception {

		cmnDriver = new CommonDriver("chrome");

		cmnDriver.setPageloadTimeout(90);

		cmnDriver.setElementDetectionTimeout(20);

		cmnDriver.navigateToUrl("https://amazon.in");

		homepage = new AmazonHomepagePOM(cmnDriver.getDriver());

	}

	@AfterMethod
	public void closeBrowser() throws Exception {

		cmnDriver.closeAllBrowsers();
	}

	@Test
	public void searchProduct() throws Exception {

		String product = "Apple Watch";

		String category = "Electronics";
		

		homepage.searchProduct(product, category);

	}

}
