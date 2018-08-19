package com.qtpselenum.zoho.keywords;

import java.util.Hashtable;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.zoho.util.Xls_Reader;

public class DriverScript {
       ExtentTest test;
       AppKeywords app;
       
       public DriverScript(ExtentTest t){
    	   test=t;
       }
	
      
		public void executetest(String testname, Xls_Reader xls,Hashtable<String,String> table){
    
    int rows=xls.getRowCount("Keywords");
    app=new AppKeywords(test);  //init the property file
     for(int rnum=2;rnum<=rows;rnum++){
    	String tcid=xls.getCellData("Keywords", "TCID", rnum);
    	if(tcid.equals(testname)){
    	String keyword=xls.getCellData("Keywords", "Keyword", rnum);
    	String objectkey=xls.getCellData("Keywords", "Object", rnum);
    	String datakey=xls.getCellData("Keywords", "Data", rnum);
    	String data=table.get(datakey);
    	test.log(LogStatus.INFO, (tcid+"---"+keyword+"----"+objectkey+"----"+data+"----"));
    	//System.out.println(keyword);
    	//System.out.println(object);
    	//System.out.println(data);
    
if(keyword.equals("openbrowser"))
	app.openBrowser(data);
    else if(keyword.equals("navigate"))
	app.navigate(objectkey); 
	else if(keyword.equals("click"))
		app.click(objectkey);
	else if(keyword.equals("input"))
		app.input(objectkey,data);
	else if(keyword.equals("switchtoframe"))
		app.switchtoframe(objectkey);
	else if(keyword.equals("exitframe"))
		app.exitframe();
	else if(keyword.equals("verifyLogin"))
		app.verifylogin(table.get("ExpectedResult"));
	else if(keyword.equals("defaultLogin"))
		app.defaultlogin();
    }

	}
		}


		public void quit() {
			app.quit();
			
		}
}

