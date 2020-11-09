package in.amazon.demo;

import org.testng.annotations.Test;

import commonLibs.utils.ExcelDriver;

public class DemoExcelDriver {
	
	ExcelDriver excelDriver;
	
	String workbookName;
	
	String sheetname;
	
	@Test
	public void verifyExcelDriver() {
		
		try {
			workbookName = System.getProperty("user.dir") + "/testDataSource/testData.xlsx";
			
			sheetname = "Test Data";
			
			excelDriver = new ExcelDriver();
			
			excelDriver.createWorkbook(workbookName);
			
			excelDriver.openWorkbook(workbookName);
			
			excelDriver.createSheet(sheetname);
			
			excelDriver.setCellData(sheetname, 0, 0, "Saurabh");
			excelDriver.setCellData(sheetname, 1, 0, "Sahil");
			excelDriver.setCellData(sheetname, 2, 0, "Vivek");
			excelDriver.setCellData(sheetname, 3, 0, "Prerna");
			
			excelDriver.save();
			
			excelDriver.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
