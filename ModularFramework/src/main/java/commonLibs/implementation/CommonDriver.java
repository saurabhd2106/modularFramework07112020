package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import commonLibs.contracts.IDriver;

public class CommonDriver implements IDriver {

	private WebDriver driver;

	private int pageloadTimeout;
	private int elementDetectionTimeout;

	private String currentWorkingDirectory;

	public void setPageloadTimeout(int pageloadTimeout) {

		if (pageloadTimeout > 0) {
			this.pageloadTimeout = pageloadTimeout;

		}
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {

		if (elementDetectionTimeout > 0) {
			this.elementDetectionTimeout = elementDetectionTimeout;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public CommonDriver(String browserType) throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");

		pageloadTimeout = 60;
		elementDetectionTimeout = 10;

		browserType = browserType.trim();

		if (browserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browserType.equalsIgnoreCase("chrome-headless")) {

			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");

			ChromeOptions chrOptions = new ChromeOptions();

			chrOptions.addArguments("--headless");

			driver = new ChromeDriver(chrOptions);

		} else if (browserType.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/msedgedriver.exe");

			driver = new EdgeDriver();

		} else {
			throw new Exception("Invalid Browser Type " + browserType);
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

	}

	@Override
	public void navigateToUrl(String url) throws Exception {

		url = url.trim();

		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		driver.get(url);

	}

	@Override
	public void refresh() throws Exception {

		driver.navigate().refresh();

	}

	@Override
	public void navigateBack() throws Exception {
		driver.navigate().back();

	}

	@Override
	public void navigateForward() throws Exception {
		driver.navigate().forward();

	}

	@Override
	public String getTitle() throws Exception {

		return driver.getTitle();
	}

	@Override
	public String getCurrentUrl() throws Exception {

		return driver.getCurrentUrl();
	}

	@Override
	public void closeBrowser() throws Exception {

		if (driver != null) {
			driver.close();
		}

	}

	@Override
	public void closeAllBrowsers() throws Exception {
		if (driver != null) {
			driver.quit();
		}

	}

}
