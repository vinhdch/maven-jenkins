package guru.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneraterManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumberOptions.Hooks;
import pageObjects.HomePageObject;

public class HomePageSteps {
	WebDriver driver;
	String loginPageUrl;
	HomePageObject homePageObject;
	TestContext testContext;

	public HomePageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		homePageObject = PageGeneraterManager.getHomePageObject(driver);
	}

	@Given("^Open regitser page$")
	public void openRegitserPage() {
		homePageObject.openRegitserPage();
	}

	@Then("^Home page displayed$")
	public void homePageDisplayed() {
		Assert.assertFalse(homePageObject.isWelcomeMessageDisplayed());
	}

	@Then("^Get home page$")
	public void getHomePage() {
		// dung thu vien picoContainer de share data trong cucumber framework
		// 2 ways to share data: declare static variable (share giua cac scenario) or using testContext (share giua cac class)
		testContext.getDataContext().setContext(Context.LOGIN_URL, homePageObject.getHomePageUrll());
	}

}
