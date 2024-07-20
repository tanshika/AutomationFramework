package com.PracticeFramework.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static String generateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
		File excelFile = new File("/Users/vipulbhardwaj/Desktop/JavaTesting/PracticeFramework1/src/main/java/com/PracticeFramework/qa/testdata/TestData.xlsx");
		FileInputStream fisEcxel = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fisEcxel);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rows][columns];
		
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for (int j = 0; j < columns; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = "./Screenshots/" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}
	
}
