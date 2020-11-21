package in.amazon.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;

import commonLibs.implementation.CommonDriver;
import commonLibs.implementation.ScreenshotControl;
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

	ScreenshotControl screenshotControl;

	@BeforeSuite
	public void preSetup() throws Exception {

		initializeDefaultVariables();

		initializeReport();

	}

	@AfterSuite
	public void postCleanup() throws Exception {

		closeReport();

	}

	@Parameters("browserType")
	@BeforeMethod
	public void setup(String browserType) throws Exception {

		invokeBrowser(browserType);

		initializeScreenshotVariable();

		initializePages();

	}

	private void initializeScreenshotVariable() {

		screenshotControl = new ScreenshotControl(driver);

	}

	@AfterMethod
	public void cleanUp(ITestResult testResutlt) throws Exception {

		String testcaseName = testResutlt.getName();

		if (testResutlt.getStatus() == ITestResult.SUCCESS) {
			extentReportUtils.addLog(Status.PASS, "All test step passed...");
		} else if (testResutlt.getStatus() == ITestResult.FAILURE) {
			extentReportUtils.addLog(Status.FAIL, "One or more test step failed..");

			String executiontime = DateUtils.getCurentTime();
			String screenshotFilename = String.format("%s/screenshots/%s-%s.jpeg", currentWorkingDirectory,
					testcaseName, executiontime);

			screenshotControl.captureAndSaveScreenshot(screenshotFilename);

			extentReportUtils.addScreenshotToReport(screenshotFilename);

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

	private void invokeBrowser(String browserType) throws Exception {
		extentReportUtils.createTest("Setup - Setting up before a test case");

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
