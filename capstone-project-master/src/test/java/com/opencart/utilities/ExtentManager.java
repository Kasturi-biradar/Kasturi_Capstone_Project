package com.opencart.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

	// Singleton to avoid multiple instances
	public static ExtentReports getInstance() {
		if (extent == null) {
			String projectPath = System.getProperty("user.dir");
			String reportPath = projectPath + "/test-output/ExtentReport.html";

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setDocumentTitle("OpenCart Automation Report");
			spark.config().setReportName("OpenCart Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project", "OpenCart Capstone");
			extent.setSystemInfo("Tester", "Your Name");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}
}
