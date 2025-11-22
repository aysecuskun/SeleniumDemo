package EticaretAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDemo {


		WebDriver driver;
		
		@BeforeClass
	void setup() {
		driver=new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 
		 
	}
		@Test(dataProvider="dp")
	void testLogin(String email, String password) throws InterruptedException {
		driver.get("https://www.trendyol.com/giris?cb=%2F");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"login-email\"]")).sendKeys(email);
		 driver.findElement(By.xpath("//*[@id=\"login-password-input\"]")).sendKeys(password);
		 driver.findElement(By.xpath("//*[@id=\"login-register\"]/div[3]/div[1]/form/button")).click();
		 Thread.sleep(2000);
		 boolean status=driver.findElement(By.xpath("//p[text()='Hesabım']")).isDisplayed();
		 if(status==true) {
			 
			 WebElement hesabim=driver.findElement(By.xpath("//p[text()='Hesabım']"));
			 Actions act=new Actions(driver);
			 act.moveToElement(hesabim).perform();
			 Thread.sleep(1500);
			 
			 driver.findElement(By.xpath("//*[@id=\"sticky-header\"]/div/div/div[2]/div[1]/div[2]/div/div/a[12]/p")).click();
			 Assert.assertTrue(true);
		 }
		 else {
			 
			 Assert.fail();
		 }
	}
	@AfterClass
	void tearDown() {
		
		driver.close();
	}
	   @DataProvider(name="dp")
		Object[][] loginData() {
			
		   Object data[][]= {
				   
				   {"abc@gmail.com",""},
				   {"abc@gmail.com","123456"},
				   {"xyz@gmail.com","45678."},
				   {" ","123%\\*"},
		   };
		   
		   return data;
			
		}
}
