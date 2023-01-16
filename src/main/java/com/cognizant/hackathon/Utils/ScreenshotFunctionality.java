package com.cognizant.hackathon.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cognizant.hackathon.Base.BaseUI;

public class ScreenshotFunctionality extends BaseUI {
	public static void takeScreenShotOnFailure() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "//screenshot//error.png"));
		exttest.addScreenCaptureFromPath(System.getProperty("user.dir") + "//screenshot//error.png");
	}
}
