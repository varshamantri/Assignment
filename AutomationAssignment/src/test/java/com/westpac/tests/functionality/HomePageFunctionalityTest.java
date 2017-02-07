package com.westpac.tests.functionality;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.westpac.objectRepository.HomePageOR;
import com.westpac.tests.base.BaseTest;

public class HomePageFunctionalityTest extends BaseTest{
	
	WebDriver driver = null;
	HomePageOR homePageOR = null;
	
	@BeforeClass
	public void testSetUp() {
		this.driver = getDriver();
		this.homePageOR = new HomePageOR(this.driver);
	}
	
	@Test
	public void tc01_positive_verifyHomePageTitle() {
		
		Assert.assertEquals(this.driver.getTitle(), HomePageOR.getPagetitle());
	}

}
