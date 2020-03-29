import java.util.Hashtable;

// www.qtpselenium.com/downloads/poi.rar
public class ReadingData {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("F:\\temp\\Data.xlsx");
		int rows=xls.getRowCount("Login");
		System.out.println("Total rows - "+rows);
		int cols=xls.getColumnCount("Login");
		System.out.println("Total cols - "+cols);
		
		String data = xls.getCellData("Login", "Password", 3);
		System.out.println(data);
		 data = xls.getCellData("Login", 0 , 5);
		 System.out.println(data);
		 
		 xls.setCellData("Login", "Password", 20, "appium/selenium");
		 
		 Hashtable<String,String> table = new Hashtable<String,String>();
		 table.put("name","Automation");
		 table.put("city", "london");
		 System.out.println(table.get("city"));
	}

}
