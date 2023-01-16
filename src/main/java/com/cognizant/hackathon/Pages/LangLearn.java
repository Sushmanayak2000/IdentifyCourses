package com.cognizant.hackathon.Pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.cognizant.hackathon.Base.BaseUI;
import com.cognizant.hackathon.Utils.ExcelFunctionality;

public class LangLearn extends BaseUI {

	By search = By.xpath("//input[@role='textbox']");
	By button = By.xpath("//*[@id='rendered-content']//button[2]/div");
	By more = By.xpath("(//span[text()='Show more'])[4]");
	By langs = By.xpath("//div[@id='checkbox-group']//span[contains(@class,'cds-33')]");
	By close = By.xpath("//span[text()='Close']");
	By level = By.xpath(
			"//*[@id=\"rendered-content\"]/div/div/div[1]/main/div[2]/div/div/div/div/div[1]/div/div[4]/div//span[contains(@id,'cds-react-aria-')]");

	public void learningLang() throws InterruptedException, IOException{
		exttest = report.createTest("Extract all languages and levels with counts for Learning Language");
		wait(30, search);
		driver.findElement(search).sendKeys(ExcelFunctionality.getInput(7,0));
		exttest.log(Status.PASS, "Learning Language text entered Successfully");
		driver.findElement(button).click();
		exttest.log(Status.PASS, "Search icon clicked Successfully");
		driver.findElement(more).click();
		exttest.log(Status.PASS, "Show more link for Language is clicked Successfully");
		System.out.println("");
		System.out.println("The languages are: ");
		System.out.println("");
		List<WebElement> langsAll = driver.findElements(langs);
		int languageCount = 0;
		List<String> languagesAndLevels = new ArrayList<String>();
		languagesAndLevels.add("   The languages are:  ");
		for (int i = 0; i < langsAll.size(); i++) {
			String lang=langsAll.get(i).getText();
			languagesAndLevels.add(lang);
			System.out.println(lang);
			languageCount++;
		}
		languagesAndLevels.add(" ");
		languagesAndLevels.add(" ");
		driver.findElement(close).click();
		exttest.log(Status.PASS, "All Languages are extracted Successfully");
		System.out.println("");
		System.out.println("");
		System.out.println("The levels are: ");
		System.out.println("");
		List<WebElement> levels = driver.findElements(level);
		int levelCount = 0;
		languagesAndLevels.add("  The levels are: ");
		for (int j = 0; j < levels.size(); j++) {
			String level=levels.get(j).getText();
			languagesAndLevels.add(level);
			System.out.println(level);
			levelCount++;
		}
		languagesAndLevels.add(" ");
		languagesAndLevels.add(" ");
		exttest.log(Status.PASS, "All levels are extracted Successfully");
		System.out.println("");
		System.out.println("");
		String langCount="Language count = " + languageCount;
		languagesAndLevels.add(langCount);
		System.out.println(langCount);
		String levCount="Level count = " + levelCount;
		languagesAndLevels.add(levCount);
		System.out.println(levCount);
		ExcelFunctionality.outputExcel("Language_Learning_Courses","LangLearn",languagesAndLevels);
	}
}
