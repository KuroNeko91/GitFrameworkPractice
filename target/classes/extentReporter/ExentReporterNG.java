package extentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExentReporterNG {

	static ExtentReports extent;
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Framework Practice Automation Results");
		reporter.config().setDocumentTitle("Testing results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pavle Gajic");
		return extent;
	}
}
  

