package utilityClasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import BasePage.BasePage;

public class screenshortUtility extends BasePage {

	
	

	   public static String takeScreenshort(String testCaseName)throws IOException {	
			File screenshots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String path=System.getProperty("user.dir")+"\\screenshorts\\"+ testCaseName+".png";
			File filepath = new File(path);
			FileUtils.copyFile(screenshots, filepath);	
			return path;
		   }
	
	
	
	
}
