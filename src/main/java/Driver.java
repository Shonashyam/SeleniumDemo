

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
//
	
	WebDriver driver;
	public void execute()
	{
	 // Read the excel to execute which scenario
		System.out.println("Execution Started");
	}
	
	public void LaunchBrowsers()
	{
		System.setProperty("webdriver.ie.driver", "C:\\Users\\arjun\\IEDriverServer.exe");
		
		//System.setProperty("webdriver.ie.driver", "C:\\Java\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver.exe");
		WebDriver driver = new InternetExplorerDriver();
		driver.get("https://en-gb.facebook.com/");
		
		System.out.println(driver.getTitle());	
		
		//System.out.println(driver.findElements(By.xpath("//input[@name='q']")).size());
		
		int i = driver.findElements(By.xpath("//input")).size();
		if (i> 0)
{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Shyam");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Rathod");
		
		
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//input[@name='u_0_9']")).click();
		
		
		
		driver.findElement(By.xpath("//input[@name='websubmit']")).click();
		
		
}
	}
	
	public void LogintoFacebook()
	{
		
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Driver d = new Driver();
//		ReadWriteExcel r = new ReadWriteExcel();
//		d.execute();
//		r.ReadExcelToHashMap("Google");
		
		
		Driver d = new Driver();
		
		d.LaunchBrowsers();
	}

}
