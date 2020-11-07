package commonLibs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	public void waitForSeconds(int timeoutInSeconds) {

		try {
			Thread.sleep(timeoutInSeconds * 1000);

		} catch (Exception e) {

		}
	}

	public static void waitTillElementVisible(WebDriver driver, int timeoutInSeconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitTillElementIsClickable(WebDriver driver, int timeoutInSeconds, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void waitTillAlertIsPresent(WebDriver driver, int timeoutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

		wait.until(ExpectedConditions.alertIsPresent());

	}

}
