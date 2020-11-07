package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.MouseControl;

public class BasePage {

	CommonElement elementControl;
	DropdownControl dropdownControl;
	MouseControl mouseControl;

	public BasePage(WebDriver driver) {

		elementControl = new CommonElement();

		dropdownControl = new DropdownControl();

		mouseControl = new MouseControl(driver);
	}

}
