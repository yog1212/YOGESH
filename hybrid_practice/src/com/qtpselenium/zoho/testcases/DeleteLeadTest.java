package com.qtpselenium.zoho.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenum.zoho.keywords.DriverScript;
import com.selenium.zoho.util.DataUtil;
import com.selenium.zoho.util.ExtentManager;
import com.selenium.zoho.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DeleteLeadTest {
	Xls_Reader xls= new Xls_Reader("F:\\selenium\\hybrid_framework_practice\\Hybrid_Framework_Practice.xlsx");
	DriverScript driver;
	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init(){
		rep = ExtentManager.getInstance();
		test = rep.startTest("DeleteLeadTest");
	}
	
	@AfterMethod
	public void quit(){
		rep.endTest(test);
		rep.flush();
		// browser is closed
		driver.quit();
	}
	
	@Test(dataProvider="getData") 
	public void deleteLeadTest(Hashtable<String,String> data){	
		if(data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode was N");
			throw new SkipException("Skipping the test as Runmode was N");
		}
		test.log(LogStatus.INFO, "Starting DeleteLeadTest ");
		driver = new DriverScript(test);
		driver.executetest("DeleteLeadTest", xls,data);
		test.log(LogStatus.PASS, "Ending DeleteLeadTest ");
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData("DeleteLeadTest", xls);
	}
}
