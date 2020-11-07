package designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;

public class AmazonHomepagePOM {
	
	private WebElement searchBox;
	
	private WebElement searchButton;
	
	private WebElement searchCategory;
	
	private WebElement searchResult;
	
	private CommonElement elementControl;
	private DropdownControl dropdownControl;
	
	
	public AmazonHomepagePOM(WebDriver driver) {
		
		searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		
		searchCategory = driver.findElement(By.id("searchDropdownBox"));
		
		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		
		searchResult = driver.findElement(By.xpath("//span[@data-component-type='s-result-info-bar']"));
		
		elementControl = new CommonElement();
		
		dropdownControl = new DropdownControl();
		
	}
	
	public void searchProduct(String product, String category) throws Exception{
		
		elementControl.setText(searchBox, product);
		
		dropdownControl.selectViaVisibleText(searchCategory, category);
		
		elementControl.clickElement(searchButton);
		
		String result = elementControl.getText(searchResult);
		
		System.out.println(result);
		
	}

}
