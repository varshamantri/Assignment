package com.westpac.tests.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	private static WebDriver driver;
	public static String workingDir = System.getProperty("user.dir");

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browser, String url) {
		if(browser.equalsIgnoreCase("Firefox")) {
			driver = initFirefoxDriver(url);
		}
		
	}

	private static WebDriver initFirefoxDriver(String url) {
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		return driver;
	}

	@Parameters({"browser", "url"})
	@BeforeSuite
	public void initializeTestBaseSetup(String browser, String url) {

		try {
			setDriver(browser, url);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
