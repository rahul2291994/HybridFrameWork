package selinium_2.pack;

import java.io.IOException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Test1 extends BaseTest 
{
	
	public static void main(String[] args) throws IOException
	{
		init();
		ExtentTest test = report.startTest("Test1");
		
		launch("chromebrowser");
		test.log(LogStatus.INFO, "launched the browser : " + p.getProperty("chromebrowser"));

		navigateUrl("amazonurl");
		test.log(LogStatus.INFO, "navigated to url : " + p.getProperty("amazonurl"));
		
		dropdown("amazon_dropdown1_id","amazon_dropdown1_value");
		test.log(LogStatus.ERROR, "dropdown selected value is " + v.getProperty("amazon_dropdown1_value")+ " using locator: " + op.getProperty("amazon_dropdown1_id"));
		
		textbox("amazon_textbox1_xpath","amazon_textbox1_value");
		test.log(LogStatus.FATAL, "textbox value entered " + v.getProperty("amazon_textbox1_xpath")+ " using locator: " + op.getProperty("amazon_textbox1_value"));
		
		searchbutton("amazon_searchbutton1_id");
		test.log(LogStatus.PASS, " clicked search button using locator: " + op.getProperty("amazon_searchbutton1_id"));
		
		link("amazon_link1_xpath");
		test.log(LogStatus.PASS, " clicked link using locator: " + op.getProperty("amazon_link1_xpath"));
		
		report.endTest(test);
		report.flush();
	}
}
