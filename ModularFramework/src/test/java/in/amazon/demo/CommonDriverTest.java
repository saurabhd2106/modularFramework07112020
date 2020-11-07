package in.amazon.demo;

import org.testng.annotations.Test;

import commonLibs.contracts.IDriver;
import commonLibs.implementation.CommonDriver;

public class CommonDriverTest {
	
	CommonDriver cmnDriver;
	
	
	@Test
	public void verifyCommonDriver() throws Exception{
		
		cmnDriver = new CommonDriver("chrome");
		
		cmnDriver.setPageloadTimeout(90);
		
		cmnDriver.setElementDetectionTimeout(20);
		
		cmnDriver.navigateToUrl("https://amazon.in");
		
		String title = cmnDriver.getTitle();
		
		System.out.println(title);
		
		cmnDriver.closeBrowser();
		
	}

}
