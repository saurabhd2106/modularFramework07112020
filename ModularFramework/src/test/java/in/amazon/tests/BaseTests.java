package in.amazon.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ConfigFileUtils;
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

	String configFilename;
	Properties configProperties;

	@BeforeSuite
	public void preSetup() throws Exception {

		initializeDefaultVariables();

		initializeReport();

	}

	@AfterSuite
	public void postCleanup() throws Exception {

		closeReport();

	}

	@BeforeMethod
	public void setup() throws Exception {

		invokeBrowser();

		initializePages();

	}

	@AfterMethod
	public void cleanUp(ITestResult testResutlt) throws Exception {

		if (testResutlt.getStatus() == ITestResult.SUCCESS) {
			extentReportUtils.addLog(Status.PASS, "All test step passed...");
		} else if (testResutlt.getStatus() == ITestResult.FAILURE) {
			extentReportUtils.addLog(Status.FAIL, "One or more test step failed..");
		} else {
			extentReportUtils.addLog(Status.SKIP, "One or more test step is skipped...");
		}

		cmnDriver.closeAllBrowsers();
	}

	private void initializeDefaultVariables() throws Exception {
		executionStartTime = DateUtils.getCurentTime();

		currentWorkingDirectory = System.getProperty("user.dir");

		configFilename = String.format("%s/config/config.properties", currentWorkingDirectory);

		configProperties = ConfigFileUtils.configFileReader(configFilename);

	}

	private void initializeReport() {
		htmlReportFilename = String.format("%s/reports/Amazon-report-%s.html", currentWorkingDirectory,
				executionStartTime);

		extentReportUtils = new ExtentReportUtils(htmlReportFilename);

	}

	private void closeReport() {
		extentReportUtils.closeReport();

	}

	private void initializePages() throws Exception {
		homepage = new AmazonHomepage(driver);

	}

	private void invokeBrowser() throws Exception {
		extentReportUtils.createTest("Setup - Setting up before a test case");

		String browserType = configProperties.getProperty("browserType");

		extentReportUtils.addLog(Status.INFO, "Browser invoked is " + browserType);
		cmnDriver = new CommonDriver(browserType);

		int pageloadTimeout = Integer.parseInt(configProperties.getProperty("pageloadtimeout"));
		extentReportUtils.addLog(Status.INFO, "Pageload timeout set is " + pageloadTimeout);
		cmnDriver.setPageloadTimeout(pageloadTimeout);

		int implicitWait = Integer.parseInt(configProperties.getProperty("elementDetectionTimeout"));
		extentReportUtils.addLog(Status.INFO, "Implict wait set is " + implicitWait);
		cmnDriver.setElementDetectionTimeout(implicitWait);

		String baseUrl = configProperties.getProperty("baseUrl");

		extentReportUtils.addLog(Status.INFO, "Base URL is " + baseUrl);
		cmnDriver.navigateToUrl(baseUrl);

		driver = cmnDriver.getDriver();

	}
}
