package utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentreports extends basepage {
	static ExtentReports extent;

	public static ExtentReports getReportObject() {

		Timestamp ts = new Timestamp(new Date().getTime());
		SimpleDateFormat date_time = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss.mmm");

		String path = System.getProperty("user.dir") + "\\TestReports\\" + date_time.format(ts);

		path = path + "\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Green Cart Automation Report");
		reporter.config().setDocumentTitle("Test results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshay");
		return extent;

	}

}
