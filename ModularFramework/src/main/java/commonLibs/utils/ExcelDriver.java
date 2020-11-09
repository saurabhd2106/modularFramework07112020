package commonLibs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriver {

	private InputStream fileReader;

	private OutputStream fileWriter;

	private Workbook excelWorkbook;

	private String excelsheet;

	public void createWorkbook(String workbookName) throws Exception {

		workbookName = workbookName.trim();

		File file = new File(workbookName);

		if (file.exists()) {
			throw new Exception("File already exists...");
		}

		if (workbookName.endsWith(".xls")) {
			excelWorkbook = new HSSFWorkbook();
		} else if (workbookName.endsWith(".xlsx")) {
			excelWorkbook = new XSSFWorkbook();
		} else {
			throw new Exception("Invalid file format ... ");
		}

		fileWriter = new FileOutputStream(workbookName);

		excelWorkbook.write(fileWriter);

		fileWriter.close();

		excelWorkbook.close();

	}

	public void openWorkbook(String workbookName) throws Exception {

		workbookName = workbookName.trim();

		excelsheet = workbookName;

		File file = new File(workbookName);

		if (!file.exists()) {
			throw new Exception("File doesnot exist...");
		}

		fileReader = new FileInputStream(workbookName);

		excelWorkbook = WorkbookFactory.create(fileReader);
	}

	public void createSheet(String sheetname) throws Exception {

		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet != null) {
			throw new Exception("Sheet already exist...");
		}

		excelWorkbook.createSheet(sheetname);

	}

	public int getRowCount(String sheetname) throws Exception {

		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet == null) {
			throw new Exception("Sheet does not exist...");
		}

		return sheet.getLastRowNum();
	}

	public int getCellCountFromRow(String sheetname, int rowNumber) throws Exception {

		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet == null) {
			throw new Exception("Sheet does not exist...");
		}

		if (rowNumber < 0) {
			throw new Exception("Invalid row number...");
		}

		Row row;

		row = sheet.getRow(rowNumber);

		if (row == null) {
			return 0;
		} else {
			return row.getLastCellNum();
		}
	}

	public String getCellData(String sheetname, int rowNumber, int cellNumber) throws Exception {

		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet == null) {
			throw new Exception("Sheet does not exist...");
		}

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid row or cell number...");
		}

		Row row;

		row = sheet.getRow(rowNumber);

		if (row == null) {
			return "";
		}

		Cell cell = row.getCell(cellNumber);

		if (cell == null) {
			return "";
		} else {

			if (cell.getCellType() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			}

			else {
				return cell.getStringCellValue();
			}

		}
	}

	public void setCellData(String sheetname, int rowNumber, int cellNumber, String data) throws Exception {

		sheetname = sheetname.trim();

		Sheet sheet = excelWorkbook.getSheet(sheetname);

		if (sheet == null) {
			throw new Exception("Sheet does not exist...");
		}

		if (rowNumber < 0 || cellNumber < 0) {
			throw new Exception("Invalid row or cell number...");
		}

		Row row;

		row = sheet.getRow(rowNumber);

		if (row == null) {

			row = sheet.createRow(rowNumber);

			row = sheet.getRow(rowNumber);
		}

		Cell cell = row.getCell(cellNumber);

		if (cell == null) {
			cell = row.createCell(cellNumber);

			cell = row.getCell(cellNumber);
		}

		cell.setCellValue(data);
	}

	public void save() throws Exception {

		fileWriter = new FileOutputStream(excelsheet);

		excelWorkbook.write(fileWriter);

		fileWriter.close();

	}

	public void saveAs(String newWorkboofilename) throws Exception {
		fileWriter = new FileOutputStream(newWorkboofilename);

		excelWorkbook.write(fileWriter);

		fileWriter.close();
	}

	public void close() throws Exception {

		fileReader.close();

		fileWriter.close();

		excelWorkbook.close();

	}
}
