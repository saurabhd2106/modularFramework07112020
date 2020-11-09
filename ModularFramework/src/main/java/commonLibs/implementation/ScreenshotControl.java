package commonLibs.implementation;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import commonLibs.contracts.IScreenshot;

public class ScreenshotControl implements IScreenshot {
	
	TakesScreenshot camera;
	
	public ScreenshotControl(WebDriver driver) {
		
		camera = (TakesScreenshot) driver;
		
	}

	@Override
	public void captureAndSaveScreenshot(String screenshotFilename) throws Exception {
		
		screenshotFilename = screenshotFilename.trim();
		
		File imgFile, tmpFile;
		
		imgFile = new File(screenshotFilename);
		
		if(imgFile.exists()) {
			throw new Exception("File already exists.. use some other name");
		}
		
		tmpFile = camera.getScreenshotAs(OutputType.FILE);
		
		FileUtils.moveFile(tmpFile, imgFile);
		
	}

}
