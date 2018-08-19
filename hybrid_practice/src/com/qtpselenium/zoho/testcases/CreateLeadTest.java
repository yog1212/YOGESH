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

public class CreateLeadTest {
	
	Xls_Reader xls=new Xls_Reader("F:\\selenium\\hybrid_framework_practice\\Hybrid_Framework_Practice.xlsx");
	DriverScript driver;
	ExtentReports rep;
	ExtentTest test;
	
	@BeforeMethod
	public void init(){
		rep=ExtentManager.getInstance();
		test=rep.startTest("CreateLeadTest");
	}
	
	@AfterMethod
	public void quit(){
	rep.endTest(test);
    rep.flush();
    driver.quit();
	}
	
	@Test(dataProvider="getdata")
	public void CreateLeadTest(Hashtable<String,String> data){
		if(data.get("Runmode").equals("N")){
			test.log(LogStatus.SKIP, "skipping the test as runmode was N");
			throw new SkipException("Skipping the test as Runmode was N");
		}
		System.out.println(data);
		test.log(LogStatus.INFO, "Starting CreateLeadTest");
		driver=new DriverScript(test);
		driver.executetest("CreateLeadTest",xls,data);
		test.log(LogStatus.PASS, "End CreateLeadTest");
	    
	}

	@DataProvider
	public Object[][] getdata(){
		//int rows=xls.getRowCount("Keywords");
		return DataUtil.getData("CreateLeadTest", xls);	
	}


}
