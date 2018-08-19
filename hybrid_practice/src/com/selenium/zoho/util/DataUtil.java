package com.selenium.zoho.util;

import java.util.Hashtable;

public class DataUtil {

	public static Object[][] getData(String testName, Xls_Reader xls) {
		//Hashtable<String,String> table = new Hashtable<String,String>();
		//table.put("country", "India");
		//table.put("name", "Ashish");
		//System.out.println(table.get("country"));
		
		
		

		int rows = xls.getRowCount(testName);
		int cols = xls.getColumnCount(testName);
		Hashtable<String,String> table=null;
		Object[][] data = new Object[rows-1][1];
		int r=0;
		for(int rNum=2;rNum<=rows;rNum++){
			// new row
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key = xls.getCellData(testName, cNum, 1);
				String value = xls.getCellData(testName, cNum, rNum);
				table.put(key, value);
			}
			data[r][0] = table;
			r++;
		}
		// data array - data
		return data;
	}

}
