package com.cognizant.hackathon.Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.cognizant.hackathon.Base.BaseUI;
import com.cognizant.hackathon.Utils.ExcelFunctionality;
import com.cognizant.hackathon.Utils.ScreenshotFunctionality;

public class ErrorCapture extends BaseUI {

	By opt = By.xpath("//a[text()='For Enterprise']");
	By campus = By.xpath("//a[text()='For Campus']");
	By fname = By.id("FirstName");
	By lname = By.id("LastName");
	By title = By.id("Title");
	By email = By.id("Email");
	By phone = By.id("Phone");
	By comp = By.id("Company");
	By type = By.id("Institution_Type__c");
	By disc = By.id("Primary_Discipline__c");
	By country = By.id("Country");
	By state = By.id("State");
	By wbdun = By.id("What_the_lead_asked_for_on_the_website__c");
	By submit = By.xpath("//button[text()='Submit']");

	@SuppressWarnings("unused")
	public void errorReport() throws InterruptedException, IOException {
		exttest = report.createTest("Display error during invalid Email Address. ");
		wait(30, opt);
		driver.findElement(opt).click();
		wait(30, campus);
		String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(campus).sendKeys(clicklnk);
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> itr = windowHandles.iterator();
		// Storing GUID of Enterprise page window
		String enterprisePageId = itr.next();
		// Storing GUID of Campus page window
		String campusPageId = itr.next();
		// Switching selenium focus to the Product Page from Result Page
		driver.switchTo().window(campusPageId);
		exttest.log(Status.PASS, "Moved to For Campus Successfully");
		driver.findElement(fname).sendKeys(ExcelFunctionality.getInput(12, 0));
		driver.findElement(lname).sendKeys(ExcelFunctionality.getInput(12, 1));
		driver.findElement(title).sendKeys(ExcelFunctionality.getInput(12, 2));
		driver.findElement(email).sendKeys(ExcelFunctionality.getInput(12, 3));
		driver.findElement(phone).sendKeys(ExcelFunctionality.getInput(12, 4));
		driver.findElement(comp).sendKeys(ExcelFunctionality.getInput(12, 5));
		Select s2 = new Select(driver.findElement(type));
		s2.selectByVisibleText(ExcelFunctionality.getInput(12, 6));
		Select s3 = new Select(driver.findElement(disc));
		s3.selectByVisibleText(ExcelFunctionality.getInput(12, 7));
		Select s4 = new Select(driver.findElement(country));
		s4.selectByVisibleText(ExcelFunctionality.getInput(12, 8));
		Select s5 = new Select(driver.findElement(state));
		s5.selectByVisibleText(ExcelFunctionality.getInput(12, 9));
		Select s6 = new Select(driver.findElement(wbdun));
		s6.selectByVisibleText(ExcelFunctionality.getInput(12, 10));
		driver.findElement(submit).click();
		Thread.sleep(2000);
		exttest.log(Status.PASS, "Data entered and submitted Successfully");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-180)", "");
		ScreenshotFunctionality.takeScreenShotOnFailure();
		exttest.log(Status.PASS, "Screenshot taken Successfully");
		String errorMsg = driver.findElement(By.id("ValidMsgEmail")).getText();
		System.out.println("");
		System.out.println("Error Message :");
		System.out.println(errorMsg);
		System.out.println();
		System.out.println("Automation Completed Successfully");
		System.out.println("");
	}
}