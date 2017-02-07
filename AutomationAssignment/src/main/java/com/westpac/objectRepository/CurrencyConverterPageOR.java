package com.westpac.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrencyConverterPageOR {
	
	private WebDriver driver;

	public CurrencyConverterPageOR(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver,this);
	}
	
	private static final String pageTitle = "Currency converter | International & Migrant - Westpac NZ";
	private static final String expectedBlankErrorMessage = "Please enter the amount you want to convert.";
	
	@FindBy(how = How.ID, using = "westpac-iframe")
	private WebElement westpacFrame;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ConvertFrom']")
	private WebElement fromCurrency;
	
	@FindBy(how = How.XPATH, using = "//select[@id='ConvertTo']")
	private WebElement toCurrency;
	
	@FindBy(how = How.XPATH, using = "//input[@id='Amount']")
	private WebElement amount;
	
	@FindBy(how = How.XPATH, using = "//input[@id='convert']")
	private WebElement convertButton;
	
	@FindBy(how = How.XPATH, using = "//div[@id='errordiv']/ul/li")
	private WebElement errorMessage;
	
	@FindBy(how = How.ID, using = "resultsdiv")
	private WebElement result;
	
	public static String getPagetitle() {
		return pageTitle;
	}
	
	public static String getExpectedBlankErrorMessage() {
		return expectedBlankErrorMessage;
	}
	
	public WebElement getWestpacFrame() {
		return westpacFrame;
	}

	public WebElement getFromCurrency() {
		return fromCurrency;
	}

	public WebElement getToCurrency() {
		return toCurrency;
	}

	public WebElement getAmount() {
		return amount;
	}

	public WebElement getConvertButton() {
		return convertButton;
	}
	
	public WebElement getErrorMessage() {
		return errorMessage;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getResult() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsdiv")));
		return result;
	}

	
}
