package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilityClasses.ExcelUtility;

public class DataProviders {

	
	
	 @DataProvider(name = "excelData")
	    public Object[][] getDataFromExcel() throws IOException {
	        // Dynamic file path and sheet name
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\MobileNames.xlsx";
	        String sheetName = "iphones";
	        return ExcelUtility.getExcelData(filePath, sheetName);
	    }
}
