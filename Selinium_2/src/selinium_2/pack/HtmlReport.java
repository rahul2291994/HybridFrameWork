package selinium_2.pack;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class HtmlReport 
{
	public static ExtentReports report;
	public static ExtentReports getInstance()
	{
		if(report==null)
		{
			report = new ExtentReports(BaseTest.projectpath + "/HTML_Reports/Test1.html");
			report.loadConfig(new File(BaseTest.projectpath+"/extentreportconfig.xml"));
			report.addSystemInfo("Selinium Bindings", "3.21.68").addSystemInfo("HTML Version", "3.0");
		}
		
		return report;
	}
}
