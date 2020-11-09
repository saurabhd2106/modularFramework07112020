package in.amazon.utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.ExcelDriver;

public class TestDataSource {

	ExcelDriver excelDriver;

	@DataProvider
	public Object[][] getDataForSearchProduct() throws Exception {

		String workbookName = System.getProperty("user.dir") + "/testDataSource/TestDataAmazon.xlsx";

		String sheetname = "SearchProductTestData";

		return getDataFromExcel(workbookName, sheetname);

	}

	private Object[][] getDataFromExcel(String workbookName, String sheetname) throws Exception {

		excelDriver = new ExcelDriver();

		excelDriver.openWorkbook(workbookName);

		Object[][] data;

		int rowCount = excelDriver.getRowCount(sheetname);

		int cellCount = excelDriver.getCellCountFromRow(sheetname, 1);

		data = new Object[rowCount + 1][cellCount];

		for (int row = 0; row < rowCount; row++) {
			for (int cell = 0; cell < cellCount; cell++) {
				data[row][cell] = excelDriver.getCellData(sheetname, row, cell);
			}
		}

		return data;

	}

}
