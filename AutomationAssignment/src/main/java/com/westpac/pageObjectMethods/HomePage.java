package com.westpac.pageObjectMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.westpac.objectRepository.HomePageOR;

public class HomePage {
	
	private HomePageOR homePageOR;
	private WebDriver driver;
	
	public HomePage(HomePageOR homePageOR, WebDriver driver) {
		this.homePageOR = homePageOR;
		this.driver = driver;
	}

	public void hoverFxTravelMigrant() {
		Actions action = new Actions(driver);
		action.moveToElement(this.homePageOR.getFxTravelMigrant());
		action.perform();
	}
	
	public void clickCurrencyConverter() {
		this.homePageOR.getCurrencyConverterButton().click();
	}
}
