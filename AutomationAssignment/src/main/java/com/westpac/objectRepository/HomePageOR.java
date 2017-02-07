package com.westpac.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageOR {

	private WebDriver driver;

	public HomePageOR(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver,this);
	}
	
	private static final String pageTitle = "Bank | Westpac New Zealand - Helping Kiwis with their personal banking";

	@FindBy(how = How.XPATH, using = "//a[@id='ubermenu-section-link-fx-travel-and-migrant-ps']")
	private WebElement fxTravelMigrant;

	@FindBy(how = How.XPATH, using = "//a[@id='ubermenu-item-cta-currency-converter-ps']")
	private WebElement currencyConverterButton;
	
	public static String getPagetitle() {
		return pageTitle;
	}

	public WebElement getFxTravelMigrant() {
		return fxTravelMigrant;
	}

	public WebElement getCurrencyConverterButton() {
		return currencyConverterButton;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}



}
