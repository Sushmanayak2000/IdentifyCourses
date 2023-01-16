package com.cognizant.hackathon.Base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.cognizant.hackathon.Utils.ExtentReportManager;

public class BaseUI {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ExtentReports report=ExtentReportManager.getReportInstance();
	public static ExtentTest exttest;

	public void driverSetup() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") +"\\resources\\config.properties")); // Loading the properties
			if (prop.getProperty("browserName").matches("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver(); // Initializing the new Chrome driver
			}
			if (prop.getProperty("browserName").matches("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver(); // Initializing the new firefox driver
			}
			driver.manage().window().maximize(); // To maximize the window
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50)); // Waiting time to page the load
																				// completely
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Adding driver waits to timeouts
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void openUrl() // Method to open URL for smoke test
	{
		driver.get(prop.getProperty("url"));
	}

	// Function to Put Wait
	public void wait(int sec, By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// method to close the browser
	public void quitBrowser() {

		report.flush();
		driver.quit(); // To close the browser
	}

}
