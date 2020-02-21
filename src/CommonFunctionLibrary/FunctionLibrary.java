
package CommonFunctionLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertiesFileUtilities;



public class FunctionLibrary {

	static WebDriver driver;
	
	public static WebDriver startBrowser() throws Exception{
		if(PropertiesFileUtilities.getValueForKey("Browser").equalsIgnoreCase("chrome"))
				{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Prathyusha\\SA_Hybrid\\Driver\\chromedriver.exe");
		 driver=new ChromeDriver();
				}
		 else if(PropertiesFileUtilities.getValueForKey("Browser").equalsIgnoreCase("firefox"))
				 {
			 System.setProperty("webdriver.gecko.driver", "D:\\Selenium_Prathyusha\\SA_Hybrid\\Driver\\geckodriver.exe");
			 driver=new FirefoxDriver();
		 }else
		 {
			 System.setProperty("webdriver.ie.driver", "D:\\Selenium_Prathyusha\\SA_Hybrid\\Driver\\IEDriverServer.exe");
			 driver=new InternetExplorerDriver();
		 }
		return driver;
			 
		 }
	public static void OpenApplication(WebDriver driver) throws Exception{
		driver.get(PropertiesFileUtilities.getValueForKey("url"));
		driver.manage().window().maximize();
		}
	public static void waitForElement(WebDriver driver,String locatertype,String locatervalue,String waittime ){
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittime));
		if(locatertype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatervalue)));
		}else if(locatertype.equalsIgnoreCase("xpath")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatervalue)));
		}else if(locatertype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatervalue)));
		}else
		{
		System.out.println("unable to locate for waitForElement method with "+locatertype);
		}	
	}
	public static void typeAction(WebDriver driver,String locatertype, String locatervalue,String testdata){
		if(locatertype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatervalue)).clear();
			driver.findElement(By.id(locatervalue)).sendKeys(testdata);
		}
		else if(locatertype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatervalue)).clear();
			driver.findElement(By.xpath(locatervalue)).sendKeys(testdata);
		}
		else if(locatertype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatervalue)).clear();
			driver.findElement(By.name(locatervalue)).sendKeys(testdata);
		}
		else if(locatertype.equalsIgnoreCase("tagname")){
			driver.findElement(By.tagName(locatervalue)).clear();
			driver.findElement(By.tagName(locatervalue)).sendKeys(testdata);
		}else
		{
		System.out.println("unable to locate for waitForElement method with "+locatertype);
		}
	}
	
	
	public static void clickAction(WebDriver driver,String locatertype,String locatervalue){
		if(locatertype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatervalue)).click();
		}
		else if(locatertype.equalsIgnoreCase("xpath")){
			driver.findElement(By.xpath(locatervalue)).click();
		}
		else if(locatertype.equalsIgnoreCase("name")){
			driver.findElement(By.name(locatervalue)).click();
		}else
		{
		System.out.println("unable to locate for clickAction method with "+locatertype);
		}
	}
	
	public static void tableValidation(WebDriver driver,String column) throws Exception{
		
		FileReader fr=new FileReader("D:\\Selenium_Prathyusha\\SA_Hybrid\\CaptureData\\SupplierNum.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		
		if(driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("searchtextbox"))).isDisplayed()){
		Thread.sleep(5000);
		driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("searchtextbox"))).sendKeys(exp_data);
		driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("searchbutton"))).click();
		}else{
			
			driver.findElement(By.xpath(PropertiesFileUtilities.getValueForKey("searchpanelbutton"))).click();
			Thread.sleep(5000);
			driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("searchtextbox"))).sendKeys(exp_data);
			driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("searchbutton"))).click();
		}

		WebElement table=driver.findElement(By.id(PropertiesFileUtilities.getValueForKey("suppliertable")));
		
		List<WebElement>rows=table.findElements(By.tagName("tr"));
		
		for(int i=1;i<rows.size();i++){
		       String act_data= driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column+"]/div/span")).getText();	
		       Assert.assertEquals(exp_data, act_data); 
		       System.out.println(act_data+"   "+exp_data);
		       break;
		}
	}
	public static String genarateDate(){
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
		String requireddate=sdf.format(d);	
		return requireddate;
		
	}
	
	public static void CaptureData(WebDriver driver,String locatertype,String locatervalue) throws Exception{
		
		String supplierdata="";
		if(locatertype.equalsIgnoreCase("id")){
		supplierdata = driver.findElement(By.id(locatervalue)).getAttribute("value");
	}else if (locatertype.equalsIgnoreCase("xpath")) {
		driver.findElement(By.xpath(locatervalue)).getAttribute("value");
	}else if (locatertype.equalsIgnoreCase("name")) {
		driver.findElement(By.name(locatervalue)).getAttribute("value");
	}
		FileWriter fw=new FileWriter("D:\\Selenium_Prathyusha\\SA_Hybrid\\CaptureData\\SupplierNum.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(supplierdata);
		bw.flush();
		bw.close();
		
	}
	
	public static void closeBrowser(WebDriver driver){
		driver.close();
	}
}



