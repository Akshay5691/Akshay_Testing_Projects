package dataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	 
	 @DataProvider(name = "ProductNamesFromDataProviderWithKeyValues")
	 public Object[][] productsNameWithKeyValue() {
	     return new Object[][]{
	             { "P1", "Apple" },
	             { "P2", "Banana" },
	             { "P3", "Grapes" },
	             { "P4", "Orange" },
	             { "P5", "Carrot" },
	             { "P6", "Potato" },
	             { "P7", "Tomato" }
	     };
	 }
	
	 
	 @DataProvider(name = "ProductNamesFromDataProviderUsingHashMap")
	 public Object[][] productsNameUsingHashMap() {

	     Map<Integer, String> productMap = new HashMap<>();

	     productMap.put(1, "Apple");
	     productMap.put(2, "Banana");
	     productMap.put(3, "Grapes");
	     productMap.put(4, "Orange");
	     productMap.put(5, "Carrot");
	     productMap.put(6, "Potato");
	     productMap.put(7, "Tomato");

	     Object[][] data = new Object[productMap.size()][1];

	     int index = 0;
	     for (String product : productMap.values()) {
	         data[index++][0] = product;
	     }

	     return data;
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
