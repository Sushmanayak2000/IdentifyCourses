package com.cognizant.hackathon.Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.cognizant.hackathon.Base.BaseUI;
import com.cognizant.hackathon.Utils.ExcelFunctionality;

public class Courses extends BaseUI {
	
	By search = By.xpath("//input[@role='textbox']");
	By button = By.xpath("//*[@id='rendered-content']//button[2]/div");
	By Beginner = By.xpath("//span[text()='Beginner']");
	By more = By.xpath("(//span[text()='Show more'])[4]");
	By english = By.xpath("//span[text()='English']");
	By apply = By.xpath("//span[text()='Apply']");
	By name = By.xpath("//a/div/div/div/h2");
	By rating = By.xpath("//div[contains(@class,'css-pn23ng')]/p[contains(@class,'cds-33')][1]");
	By learnPeriod = By.xpath("//div[contains(@class,'css-pn23ng')]/following-sibling::p");

	public void find() throws InterruptedException, IOException {
		exttest = report.createTest("Display two courses for Beginner Web Development");
		wait(30, search);
		driver.findElement(search).sendKeys(ExcelFunctionality.getInput(2, 0));
		exttest.log(Status.PASS, "Web Development text entered Successfully");
		driver.findElement(button).click();
		exttest.log(Status.PASS, "Search icon clicked Successfully");
		wait(30, Beginner);
		driver.findElement(Beginner).click();
		exttest.log(Status.PASS, "Beginner checkbox clicked Successfully");
		driver.findElement(more).click();
		wait(30, english);
		driver.findElement(english).click();
		driver.findElement(apply).click();
		exttest.log(Status.PASS, "English checkbox clicked Successfully");
		System.out.println("");
		System.out.println("The courses, ratings and total learning hours are: ");
		System.out.println("");
		List<WebElement> names = driver.findElements(name);
		exttest.log(Status.PASS, "Course names extracted Successfully");
		List<WebElement> ratings = driver.findElements(rating);
		exttest.log(Status.PASS, "Course ratings extracted Successfully");
		List<WebElement> learnP = driver.findElements(learnPeriod);
		exttest.log(Status.PASS, "Course learning period extracted Successfully");
		List<String> coursesInfo = new ArrayList<String>();
		for (int i = 0; i < 2; i++) {
			String[] a = learnP.get(i).getText().split("·");
			String course = names.get(i).getText() + " || " + ratings.get(i).getText() + " || " + a[2];
			coursesInfo.add(course);
			System.out.println(course);
		}
		ExcelFunctionality.outputExcel("Web_Development_Courses", "WebDev", coursesInfo);
		exttest.log(Status.PASS, "Courses are displayed Successfully");
	}
}
