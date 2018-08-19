package com.qtpselenum.zoho.keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericKeywords {
	
	WebDriver driver;
	Properties prop;
	ExtentTest test;
	

	public void openBrowser(String browserType){
		test.log(LogStatus.INFO,"Opening Browser");
		
		if(prop.getProperty("gridRun").equals("Y")){
			//hit the hub
			DesiredCapabilities cap=null; // messenger
			if(browserType.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browserType.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setJavascriptEnabled(true);
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				 ChromeOptions options = new ChromeOptions();
				 options.addArguments("test-type");
				 options.addArguments("start-maximized");
				 options.addArguments("--enable-automation");
				 options.addArguments("test-type=browser");
				 options.addArguments("disable-infobars");
				 //System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"chromedriver.exe");
			}else if(browserType.equals("IE")){
				cap = DesiredCapabilities.internetExplorer();
				 cap.setBrowserName("internetexplorer");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{		
			
			if(browserType.equals("Mozilla")){
				driver = new FirefoxDriver();
			    driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);}
			else if(browserType.equals("Chrome")){
				//System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				 options.addArguments("test-type");
				 options.addArguments("start-maximized");
				 options.addArguments("--enable-automation");
				 options.addArguments("test-type=browser");
				 options.addArguments("disable-infobars");
				 driver = new ChromeDriver(options);
			}else if(browserType.equals("IE")){
				driver = new InternetExplorerDriver();
			}
		}
		
	}
	public void navigate(String urlkey){
		test.log(LogStatus.INFO, "navigating"+prop.getProperty(urlkey));
		driver.get(prop.getProperty(urlkey));
	}
	public void click(String xpthexprkey){
		test.log(LogStatus.INFO, "clicking"+prop.getProperty(xpthexprkey));
		getobject(xpthexprkey).click();
	}
	public void input(String xpthexprkey,String  data){
		test.log(LogStatus.INFO, "typing"+prop.getProperty(xpthexprkey));
		getobject(xpthexprkey).sendKeys(data);
	}
	
	public void switchtoframe(String xpthexprkey){
		//auto calculate frame and switch to frame
		int totalframe=driver.findElements(By.tagName("iframe")).size();
		
		for(int i=0;i<totalframe;i++){
			driver.switchTo().frame(i);
			int t=driver.findElements(By.xpath(prop.getProperty(xpthexprkey))).size();
			if(t==0){//not found
				driver.switchTo().defaultContent();
			}
			else {//found
				return;
			}
			
		}
		
			
				
	}
	public void exitframe(){
		test.log(LogStatus.INFO,"exit");
		driver.switchTo().defaultContent();
	}
	
	
	//common function
	public WebElement getobject(String xpathExprkey){
		WebElement e=null;
		try{
			if(xpathExprkey.endsWith("_xpath")){
		e=driver.findElement(By.xpath(prop.getProperty(xpathExprkey)));
			}else if(xpathExprkey.endsWith("_id")){
				e=driver.findElement(By.id(prop.getProperty(xpathExprkey)));
			}else if(xpathExprkey.endsWith("_name")){
				e=driver.findElement(By.name(prop.getProperty(xpathExprkey)));
			}
		}catch(Exception e1){
			//e1.printStackTrace();
		   reportFailure("object not found"+xpathExprkey);
				
			
		}
		return e;
	}
	
	public boolean isElementPresent(String xpathKey){
		int s = driver.findElements(By.xpath(prop.getProperty(xpathKey))).size();
		if(s==0)
			return false;
		else
			return true;
	}

// reporting functions
	
	 void reportFailure(String e1) {
		// embedded snapshot
		takeScreenShot();
		//fail
		test.log(LogStatus.FAIL, e1);
		Assert.fail(e1);
	}

	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//put screenshot file in reports
				test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));

	}
	public void quit() {
		if(driver!=null){
			driver.quit();
			driver=null;
		}	
	}
}