package com.plivo.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.plivo.qa.base.TestBase;



public class TestUtil extends TestBase {
	
	
	public static long Page_Load_Timeouts=30;
	public static long Implicitly_Wait=30;
	public static int  Timeouts=30;
	public static String TESTDATA_SHEET_PATH = "/home/justdial/github/POM/ZohoCrm/src/main"
			+ "/java/com/zoho/qa/testdata/zohocrmdata.xlsx";
	//path of excel
	 
	static Workbook book;
	static Sheet sheet;
	
	//Click on utility to avoid exception
	public static void clickOn(WebDriver driver ,WebElement element,int timeout){
		new WebDriverWait(driver,timeout).ignoring(StaleElementReferenceException.class).
		until(ExpectedConditions.elementToBeClickable(element));
		element.click();		
	}
	
	
	//Utility for fetching data from sheet
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//object type array
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {					//row
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {   //col
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();  //store elements in object aray
				// System.out.println(data[i][k]);
			}
		}
		return data;  //return object array
	}
	
	//take screenshot utility
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		//copy file current dir into file utils method
		//screenshots is the folder
		//Current time
		//file type
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		
		}

	

}
