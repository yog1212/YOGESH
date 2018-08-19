import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class aug {

	public static void main(String[] args) {
		DesiredCapabilities cap=null;
		cap = DesiredCapabilities.chrome();
		 cap.setBrowserName("chrome");
		 cap.setJavascriptEnabled(true);
		 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		 
		System.setProperty("webdriver.chrome.driver", "F:\\drivers\\chromedriver.exe");
		ChromeDriver fr=new ChromeDriver();
		//FirefoxDriver fr=new FirefoxDriver();
		fr.manage().window().maximize();
		fr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		fr.get("https://www.zoho.com/");
        fr.findElement(By.xpath("//a[@class='zh-login']")).click();
        fr.findElement(By.xpath("//*[@id='lid']")).sendKeys("yy");
	}

}
