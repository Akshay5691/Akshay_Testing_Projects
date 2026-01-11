package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import basePage.BasePage;
public class screenshortUtility {

	
	

	  // Pass WebDriver explicitly
    public static String takeScreenshort(WebDriver driver, String testCaseName) {
        String path = "";
        try {
           
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
            String timestamp = LocalDateTime.now().format(dtf);

            // Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create folder if not exists
            File dir = new File(System.getProperty("user.dir") + "/screenshots/" + timestamp);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Full path to save screenshot
            path = dir.getAbsolutePath() + "/" + testCaseName + ".png";

            // Copy file
            FileUtils.copyFile(src, new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}