package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtility;

public class DataProviders {

	
	 @DataProvider(name = "ProductNamesFromDataProvider")
	    public Object[][] productsName() {
	        return new Object[][]{
	                {"Apple"},
	                {"Banana"},
	                {"Grapes"},
	                {"Orange"},
	                {"Carrot"},
	                {"Potato"},
	                {"Tomato"},               
	        };
	    }	
	
	 
	 
	 @DataProvider(name = "productsNamesFromExcel")
	    public Object[][] getProductsNamesFromExcel() throws IOException {
	        // Dynamic file path and sheet name
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ProductsData.xlsx";
	        String sheetName = "ProductsNames";
	        return ExcelUtility.getExcelData(filePath, sheetName);
	    }
	 
	 @DataProvider(name = "productNameFromExcel")
	    public Object[][] getProductNameFromExcel() throws IOException {
	        // Dynamic file path and sheet name
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ProductsData.xlsx";
	        String sheetName = "ProductName";
	        return ExcelUtility.getExcelData(filePath, sheetName);
	    }
	 
	 
}
