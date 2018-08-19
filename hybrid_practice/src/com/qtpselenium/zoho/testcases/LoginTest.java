package com.qtpselenium.zoho.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenum.zoho.keywords.DriverScript;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.zoho.util.DataUtil;
import com.selenium.zoho.util.ExtentManager;
import com.selenium.zoho.util.Xls_Reader;

public class LoginTest {
	
	Xls_Reader xls=new Xls_Reader("F:\\selenium\\hybrid_framework_practice\\Hybrid_Framework_Practice.xlsx");
	DriverScript driver;
	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init(){
		rep=ExtentManager.getInstance();
		test=rep.startTest("LoginTest");
	}
	
	@AfterMethod
	public void quit(){
	rep.endTest(test);
    rep.flush();
    driver.quit();
	}
	
	@Test(dataProvider="getdata")
	public void dologin(Hashtable<String,String> data){  //data is the bag name for hashtable
		if(data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "skipping the test as runmode was N");
			throw new SkipException("Skipping the test as Runmode was N");
		}
		System.out.println(data);
		test.log(LogStatus.INFO, "Starting Login Test");
		driver=new DriverScript(test);
		driver.executetest("LoginTest",xls ,data);
		test.log(LogStatus.PASS, "End Login Test");
		}
	
	
	@DataProvider
	public Object[][] getdata(){
		//int rows=xls.getRowCount("Keywords");
		return DataUtil.getData("LoginTest", xls);	
	}
}

