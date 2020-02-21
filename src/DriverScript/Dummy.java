package DriverScript;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.utils.Writer;

import CommonFunctionLibrary.FunctionLibrary;
import Utilities.PropertiesFileUtilities;

public class Dummy {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		

		ExtentReports report=new ExtentReports("C:\\Users\\pratyusha.b\\Desktop\\testing\\report.html");
		System.out.println("Execution started");
		ExtentTest writer=report.startTest("LoginTest");
		WebDriver driver1=FunctionLibrary.startBrowser();
		writer.log(LogStatus.INFO, "Execution started");
		
		FunctionLibrary.OpenApplication(driver1);
		writer.log(LogStatus.INFO, "Application opened");
		
		FunctionLibrary.waitForElement(driver1, "id", "username", "10");
		writer.log(LogStatus.INFO, "waiting for element");
		
		FunctionLibrary.typeAction(driver1, "id", "username", "admin");
		writer.log(LogStatus.INFO, "Performing Action");
		
		FunctionLibrary.waitForElement(driver1, "name", "password", "10");
		writer.log(LogStatus.INFO, "waiting for element");
		
		FunctionLibrary.typeAction(driver1, "name","password", "master");
		writer.log(LogStatus.INFO, "Performing Action");
		
		FunctionLibrary.waitForElement(driver1, "xpath", "//*[@id='btnsubmit']", "10");
		writer.log(LogStatus.INFO, "waiting for element");
		
		FunctionLibrary.clickAction(driver1, "xpath", "//*[@id='btnsubmit']");
		writer.log(LogStatus.INFO, "Performing click action");
		
		FunctionLibrary.waitForElement(driver1, "id", "logout", "10");
		writer.log(LogStatus.INFO, "Waiting for Element");
		
		FunctionLibrary.clickAction(driver1, "id", "logout");
		writer.log(LogStatus.INFO, "Performing click Action");
		
		FunctionLibrary.waitForElement(driver1, "xpath", "//*[text()='OK!']", "10");
		writer.log(LogStatus.INFO, "Waiting for element");
		
		FunctionLibrary.clickAction(driver1, "xpath","//*[text()='OK!']");
		writer.log(LogStatus.INFO, "Performing click Action");
		writer.log(LogStatus.INFO, "Performing click Action");
		
		FunctionLibrary.closeBrowser(driver1);
		writer.log(LogStatus.PASS, "Excution successful");
		report.endTest(writer);
		report.flush();
		
	}

}
