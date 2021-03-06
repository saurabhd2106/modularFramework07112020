package commonLibs.implementation;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import commonLibs.contracts.IWindow;

public class WindowControl implements IWindow {

	private WebDriver driver;

	public WindowControl(WebDriver driver) {

		this.driver = driver;
	}

	@Override
	public void switchToChildWindow(String windowhandle) throws Exception {
		driver.switchTo().window(windowhandle);
	}

	@Override
	public void switchToChildWindow(int childWindowIndex) throws Exception {

		String childWindow = driver.getWindowHandles().toArray()[childWindowIndex].toString();

		driver.switchTo().window(childWindow);

	}

	@Override
	public String getWindowHandle() throws Exception {

		return driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() throws Exception {
		return driver.getWindowHandles();
	}

}
