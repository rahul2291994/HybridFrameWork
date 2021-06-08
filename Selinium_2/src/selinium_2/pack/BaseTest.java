package selinium_2.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest
{
	public static FileInputStream fis;
	public static String projectpath= System.getProperty("user.dir");
	public static Properties p;
	public static Properties op;
	public static Properties v;
	public static WebElement wb;
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test1;
	public static void init() throws IOException
	{
		System.out.println(projectpath);
		fis = new FileInputStream(projectpath+"/env.properties");
		p = new Properties();
		p.load(fis);
		String e= p.getProperty("env");
		
		fis = new FileInputStream(projectpath + "/" + e + ".properties");
		p.load(fis);
		
		fis = new FileInputStream(projectpath + "/operation.properties");
		op = new Properties();
		op.load(fis);
		
		fis = new FileInputStream(projectpath + "/Fieldvalue.properties");
		v = new Properties();
		v.load(fis);
		
		report=HtmlReport.getInstance();
	}
	
	public static void launch (String browser)
	{		
		
		if (p.getProperty(browser).equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:/Selinium/Drivers/chromedriver_win32/chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("user-data-dir=C:/Users/Shivangi/AppData/Local/Google/Chrome/User Data/Profile 1");
			option.addArguments("--disable-notifications");
			driver = new ChromeDriver(option);			
		}
		
		if (p.getProperty(browser).equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:/Selinium/Drivers/geckodriver-v0.29.1-win32/geckodriver.exe");
			ProfilesIni pi = new ProfilesIni();
			FirefoxProfile fp = pi.getProfile("new_firefox1");
			fp.setPreference("dom.webnotifications.enabled", false);
			FirefoxOptions fo = new FirefoxOptions();
			fo.setProfile(fp);
			driver = new FirefoxDriver(fo);			
		}
				
	}
	
	public static void navigateUrl(String url)
	{
		driver.get(p.getProperty(url));
		
	}
	
	public static void dropdown(String locator, String value)
	{
		findElement(locator).sendKeys(v.getProperty(value));
	}
	
	public static void textbox(String locator, String value)
	{
		findElement(locator).sendKeys(v.getProperty(value));
	}
	
	public static void searchbutton(String locator)
	{
		findElement(locator).click();
		
	}
	
	public static void link(String locator)
	{
		findElement(locator).click();
	}
	
	
	public static WebElement findElement(String locator)
	{
		wb=null;
		if(locator.endsWith("_id"))
		{
			wb=driver.findElement(By.id(op.getProperty(locator)));
		}
		
		if(locator.endsWith("_xpath"))
		{
			wb=driver.findElement(By.xpath(op.getProperty(locator)));
		}
		
		if (locator.endsWith("_name"))
		{
			wb=driver.findElement(By.name(op.getProperty(locator)));			
		}
		return wb;
	}
	
	public static void comparelink (String expectedlinkvalue, String actuallinkvalue) throws IOException
	{
		if (expectedlinkvalue.equals(actuallinkvalue))
		success("Both links are equal");
		else 
		fail("Both links are not equal");
	}
	
	public static void success(String success_message)
	{
		test1.log(LogStatus.PASS, success_message);		
	}
	
	public static void fail(String fail_message) throws IOException
	{
		File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcfile, new File (projectpath+"/Failure_Screenshots/"+getdate()));
		test1.log(LogStatus.FAIL, fail_message+test1.addScreenCapture(projectpath+"/Failure_Screenshots/"+getdate()));
	}
	
	public static String getdate()
	{
		Date dt = new Date();
		String str = dt.toString();
		str = str.replace(" ", "_").replace(":", "_");
	    return str;
	}
	
}
