package utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Listeners extends TestListenerAdapter {
	
	String reportPath = System.getProperty("user.dir") + "/reports/myReport.html";
	public ExtentSparkReporter report;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		
		report = new ExtentSparkReporter(reportPath);
		report.config().setDocumentTitle("RestAPI Automation Report");
		report.config().setReportName("Functional Testing");
		report.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "zeinkap");
		
	}
	
	public void onTestSuccesss(ITestResult result) {
		// create new entry in report
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED - " + result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		// create new entry in report
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED - " + result.getName());
		test.log(Status.FAIL, "Test case FAILED - " + result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		// create new entry in report
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED - " + result.getName());
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
	
	
}
