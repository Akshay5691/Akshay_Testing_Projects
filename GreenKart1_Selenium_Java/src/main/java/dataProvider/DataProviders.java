package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtility;

public class DataProviders {

	
	
	 @DataProvider(name = "excelData")
	    public Object[][] getDataFromExcel() throws IOException {
	        // Dynamic file path and sheet name
	        String filePath = System.getProperty("user.dir") + "/TestDataFiles/MobileNames.xlsx";
	        String sheetName = "iphones";
	        return ExcelUtility.getExcelData(filePath, sheetName);
	    }
}
