package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import basePage.BasePage;
public class screenshortUtility extends BasePage {

	
	

	   public static String takeScreenshort(String testCaseName)throws IOException {
		   
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
		   String timestamp = LocalDateTime.now().format(dtf);

	
		   
			File screenshots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String path=System.getProperty("user.dir")+"\\screenshorts\\"+timestamp+"\\ "+testCaseName+".png";
			File filepath = new File(path);
			FileUtils.copyFile(screenshots, filepath);	
			return path;
		   }
	
	
	
	
}
