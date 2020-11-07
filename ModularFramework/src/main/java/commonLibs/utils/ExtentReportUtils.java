package commonLibs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtils {

	ExtentReports extentReports;
	ExtentHtmlReporter htmlReporter;
	ExtentTest extentTest;

	public ExtentReportUtils(String htmlReportFilename) {

		htmlReporter = new ExtentHtmlReporter(htmlReportFilename);

		extentReports = new ExtentReports();

		extentReports.attachReporter(htmlReporter);

	}

	public void createTest(String testcaseName) {

		extentTest = extentReports.createTest(testcaseName);

	}

	public void addLog(Status status, String comment) {

		extentTest.log(status, comment);

	}

	public void addScreenshotToReport(String screenshotFilename) throws Exception {
		extentTest.addScreenCaptureFromPath(screenshotFilename);
	}

	public void closeReport() {

		extentReports.flush();

	}

}
