package in.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomepage extends BasePage {

	@CacheLookup
	@FindBy(id = "searchDropdownBox")
	private WebElement searchCategory;

	@CacheLookup
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;

	@CacheLookup
	@FindBy(xpath = "//input[@value='Go']")
	private WebElement searchButton;

	@FindBy(xpath = "//span[@data-component-type='s-result-info-bar']")
	private WebElement searchResult;

	public AmazonHomepage(WebDriver driver) {

		super(driver);

		PageFactory.initElements(driver, this);

	}

	public String searchProduct(String product, String category) throws Exception {

		elementControl.setText(searchBox, product);

		dropdownControl.selectViaVisibleText(searchCategory, category);

		elementControl.clickElement(searchButton);

		String result = elementControl.getText(searchResult);

		return result;

	}

}
