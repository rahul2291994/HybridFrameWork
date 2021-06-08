package selinium_2.pack;

import java.io.IOException;

import org.openqa.selenium.By;


public class Test2 extends BaseTest
{
	
	public static String expectedLink;
	public static String actualLink;
	
	
	
	public static void main(String[] args) throws IOException
	{
		init();
		test1=report.startTest("Test2");
		launch("chromebrowser");
		navigateUrl("amazonurl");
		
		expectedLink = "new releases";
		actualLink = driver.findElement(By.linkText("New Releases")).getText();
		comparelink(expectedLink,actualLink);
		report.endTest(test1);
		report.flush();
	}
	
} 