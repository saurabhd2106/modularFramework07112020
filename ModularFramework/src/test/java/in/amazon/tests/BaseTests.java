package in.amazon.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.DateUtils;
import commonLibs.utils.ExtentReportUtils;
import in.amazon.pages.AmazonHomepage;

public class BaseTests {

	CommonDriver cmnDriver;

	AmazonHomepage homepage;

	WebDriver driver;

	String executionStartTime;
	String currentWorkingDirectory;

	String htmlReportFilename;
	ExtentReportUtils extentReportUtils;

	@BeforeSuite
	public void preSetup() throws Exception {

		executionStartTime = DateUtils.getCurentTime();
		currentWorkingDirectory = System.getProperty("user.dir");

		htmlReportFilename = String.format("%s/reports/Amazon-report-%s.html", currentWorkingDirectory,
				executionStartTime);

		extentReportUtils = new ExtentReportUtils(htmlReportFilename);

	}

	@AfterSuite
	public void postCleanup() throws Exception {
		extentReportUtils.closeReport();
	}

	@BeforeMethod
	public void setup() throws Exception {
		
		extentReportUtils.createTest("Setup - Setting up before a test case");

		String browserType = "chrome";
		
		
		extentReportUtils.addLog(Status.INFO, "Browser invoked is " + browserType);
		cmnDriver = new CommonDriver(browserType);

		int pageloadTimeout = 90;
		extentReportUtils.addLog(Status.INFO, "Pageload timeout set is " + pageloadTimeout);
		cmnDriver.setPageloadTimeout(pageloadTimeout);

		int implicitWait = 20;
		extentReportUtils.addLog(Status.INFO, "Implict wait set is " + implicitWait);
		cmnDriver.setElementDetectionTimeout(implicitWait);

		String baseUrl = "https://amazon.in";
		
		extentReportUtils.addLog(Status.INFO, "Base URL is " + baseUrl);
		cmnDriver.navigateToUrl(baseUrl);

		driver = cmnDriver.getDriver();

		homepage = new AmazonHomepage(driver);

	}

	@AfterMethod
	public void cleanUp() throws Exception {

		cmnDriver.closeAllBrowsers();
	}

}
