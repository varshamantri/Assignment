package com.westpac.pageObjectMethods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.westpac.objectRepository.CurrencyConverterPageOR;

public class CurrencyConverterPage {

	private CurrencyConverterPageOR currencyConverterPageOR;
	private WebDriver driver;

	public CurrencyConverterPage(CurrencyConverterPageOR currencyConverterPageOR, WebDriver driver) {
		this.currencyConverterPageOR = currencyConverterPageOR;
		this.driver = driver;
	}

	public void switchToWestpacFrame() {
		WebElement westpacFrame = this.currencyConverterPageOR.getWestpacFrame();
		driver.switchTo().frame(westpacFrame);
	}

	public void enterFromCurrency(String fromCurrency) throws InterruptedException {
		WebElement convertFrom = this.currencyConverterPageOR.getFromCurrency();
		Select select = new Select(convertFrom);

		List<WebElement> options = select.getOptions();
		
		for(int i=0;i<options.size();i++) {
			if(options.get(i).getText().replaceAll(" ", "").trim().equalsIgnoreCase(fromCurrency.replaceAll("\u00A0","").trim())) {
				select.selectByIndex(i);
			}
		}
		
	}

	public void enterToCurrency(String toCurrency) {
		WebElement convertTo = this.currencyConverterPageOR.getToCurrency();
		convertTo.click();
		Select select = new Select(convertTo);

		List<WebElement> options = select.getOptions();

		for(int i=0;i<options.size();i++) {
			if(options.get(i).getText().replaceAll(" ", "").trim().equalsIgnoreCase(toCurrency.replaceAll("\u00A0","").trim())) {
				select.selectByIndex(i);
			}
		}
	}

	public void enterAmount(String amount) {
		WebElement amountField = this.currencyConverterPageOR.getAmount();
		amountField.clear();
		amountField.sendKeys(amount);
	}

	public void clickConvertButton() {
		this.currencyConverterPageOR.getConvertButton().click();
	}

}
