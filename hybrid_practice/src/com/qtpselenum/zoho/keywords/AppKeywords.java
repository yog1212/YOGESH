package com.qtpselenum.zoho.keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

import com.relevantcodes.extentreports.ExtentTest;

public class AppKeywords extends GenericKeywords{
	
	public AppKeywords(ExtentTest t){
		test=t;
		String path=System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\zoho\\resources\\project.properties";
		prop =new Properties();
		
		try {
			FileInputStream fs=new FileInputStream(path);
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void verifylogin(String ExpectedResult){
		String ActualResult="";
		boolean result=isElementPresent("crmLink_xpath"); //can also directly give crmLink_xpath ,as its application dependant keyword
		if(result){
			System.out.println("login success");
			ActualResult="Y";
		}else {
			System.out.println("not able to login");
			ActualResult="N";
		}
		
		if(!ExpectedResult.equals(ActualResult) )
			reportFailure(ActualResult);
		
	}
	
	public void validateLead(){
		
	}

	public void defaultlogin() {
		
		click("signinlink_xpath");
		switchtoframe("usernameinputfield_xpath");
		input("usernameinputfield_xpath",prop .getProperty("defaultUsername"));
		input("passwordinputfield_id",prop.getProperty("defaultpassword"));
		click("submitbutton_name");
		exitframe();
		///verifylogin("crmLink_xpath");

		
	}
	public void deleteLead(String leadName) {
		driver.findElement(By.linkText(leadName)).click();
		getobject("expandMenu_xpath").click();
		getobject("deleteLead_xpath").click();
		getobject("deleteButton_id").click();
		
		
	}

}
