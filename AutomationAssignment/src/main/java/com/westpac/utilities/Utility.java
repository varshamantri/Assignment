package com.westpac.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utility {
	public static String workingDir = System.getProperty("user.dir");

	public static Object[][] readExcel(String filePath,String fileName,String sheetName) throws IOException{

		Object[][] testData = null;

		//Create an object of FileInputStream class to read excel file
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook westpacAutomation = null;

		//Find the file extension by spliting file name in substring and getting only extension name

		String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));

		//Check condition if the file is xlsx file

		if(fileExtensionName.equals(".xlsx")){
			//If it is xlsx file then create object of XSSFWorkbook class
			westpacAutomation = new XSSFWorkbook(inputStream);
		}

		//Check condition if the file is xls file

		else if(fileExtensionName.equals(".xls")){
			//If it is xls file then create object of XSSFWorkbook class
			westpacAutomation = new HSSFWorkbook(inputStream);

		}

		//Read sheet inside the workbook by its name

		Sheet currencyConversionTestDataSheet = westpacAutomation.getSheet(sheetName);

		//Find number of rows in excel file

		int rowCount = currencyConversionTestDataSheet.getLastRowNum();
		int colCount = currencyConversionTestDataSheet.getRow(0).getLastCellNum();

		testData = new Object[rowCount][colCount];

		//Create a loop over all the rows of excel file to read it

		for (int i = 1; i <= rowCount; i++) {

			Row row = currencyConversionTestDataSheet.getRow(i);

			//Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);

				if(cell!=null) {
					if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
						testData[i-1][j] = cell.getStringCellValue();
					}
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						DataFormatter format = new DataFormatter();
						testData[i-1][j] = format.formatCellValue(cell);
					}
				}
			}
		}
		return testData;
	}

	public static void captureScreenshot(String testCaseName, WebDriver driver){
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("dd MM yyyy");
		String currentDate=df.format(date);

		File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(screenshotFile, new File(workingDir+"\\screenshots\\"+testCaseName+""+currentDate+".png"));
		}
		catch(Exception e){			
		}
	}

}


