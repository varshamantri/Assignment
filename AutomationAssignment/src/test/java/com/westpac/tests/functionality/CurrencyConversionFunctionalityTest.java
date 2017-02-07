package com.westpac.tests.functionality;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.westpac.objectRepository.CurrencyConverterPageOR;
import com.westpac.objectRepository.HomePageOR;
import com.westpac.pageObjectMethods.CurrencyConverterPage;
import com.westpac.pageObjectMethods.HomePage;
import com.westpac.tests.base.BaseTest;
import com.westpac.utilities.Constants;
import com.westpac.utilities.Utility;

public class CurrencyConversionFunctionalityTest extends BaseTest{

	WebDriver driver = null;
	HomePageOR homePageOR = null;
	HomePage homePage = null;
	CurrencyConverterPageOR currencyConverterPageOR = null;
	CurrencyConverterPage currencyConverterPage = null;

	@BeforeClass
	public void testSetUp(){
		this.driver = getDriver();
		this.homePageOR = new HomePageOR(this.driver);
		this.homePage = new HomePage(this.homePageOR, this.driver);
		this.currencyConverterPageOR = new CurrencyConverterPageOR(this.driver);
		this.currencyConverterPage = new CurrencyConverterPage(this.currencyConverterPageOR, this.driver);
	}

	@Test(priority=0)
	public void tc01_positive_verifyCurrencyConversionPageTitle() throws InterruptedException {

		this.homePage.hoverFxTravelMigrant();
		this.homePage.clickCurrencyConverter();
		Thread.sleep(5000);

		Assert.assertEquals(this.driver.getTitle(), CurrencyConverterPageOR.getPagetitle());

	}

	@Test(dependsOnMethods = {"tc01_positive_verifyCurrencyConversionPageTitle"} , dataProvider="CurrencyConversionTestData", priority=1)
	public void tc02_negative_verifyBlankAmountEntered(String testCaseName, String fromCurrency, String toCurrency, String amount, String conversionRate, String actionField) {

		if(actionField.equalsIgnoreCase("BlankAmountValidation")) {
			this.currencyConverterPage.switchToWestpacFrame();
			this.currencyConverterPageOR.getAmount().clear();
			this.currencyConverterPage.clickConvertButton();

			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(this.currencyConverterPageOR.getErrorMessage()));

			String errorMessage = this.currencyConverterPageOR.getErrorMessage().getText();

			Assert.assertEquals(errorMessage, CurrencyConverterPageOR.getExpectedBlankErrorMessage());
			
			Utility.captureScreenshot(testCaseName, this.driver);

			this.driver.switchTo().defaultContent();
		}
		else {
			throw new SkipException("Skipping test case:"+testCaseName+" for action field:"+actionField);
		}

	}

	@Test(dataProvider="CurrencyConversionTestData", priority=2)
	public void tc03_positive_verifyConversionFunctionality(String testCaseName, String fromCurrency, String toCurrency, String amount, String conversionRate, String actionField) throws InterruptedException {

		if(actionField.equalsIgnoreCase("ValidConversion")) {
			this.currencyConverterPage.switchToWestpacFrame();
			this.currencyConverterPage.enterFromCurrency(fromCurrency);
			Thread.sleep(2000);
			this.currencyConverterPage.enterAmount(amount);
			Thread.sleep(1000);
			this.currencyConverterPage.enterToCurrency(toCurrency.toString());
			Thread.sleep(2000);
			this.currencyConverterPage.clickConvertButton();
			
			Thread.sleep(10000);

			WebElement resultDiv = this.currencyConverterPageOR.getResult();
			String result = resultDiv.getText();

			int startIndex = result.indexOf(fromCurrency.replaceAll("\u00A0"," ").trim());
			int endIndex = result.indexOf(toCurrency.replaceAll("\u00A0"," ").trim());

			String rate[] = result.substring(startIndex, endIndex).split("=");

			String conversionRateActual = rate[1];

			if(conversionRate.startsWith("0")){
				conversionRate  = conversionRate.substring(1);
			}
			
			Assert.assertEquals(conversionRateActual.trim(), conversionRate);
			Utility.captureScreenshot(testCaseName, this.driver);

			this.driver.switchTo().defaultContent();
		}
		else {
			throw new SkipException("Skipping test case:"+testCaseName+" for action field:"+actionField);
		}

	}

	@DataProvider(name="CurrencyConversionTestData")
	public Object[][] readTestData() {
		Object[][] currencyConversionTestDataArray = null;
		try {
			currencyConversionTestDataArray = Utility.readExcel(Utility.class.getResource("/"+Constants.CURRENCY_CONVERTER_FILE_NAME).getFile(), 
					Constants.CURRENCY_CONVERTER_FILE_NAME, Constants.CURRENCY_CONVERTER_SHEET_NAME);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return currencyConversionTestDataArray;
	}

}
