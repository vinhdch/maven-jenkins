package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;
import pageUI.RegisterPageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver mapDriver) {
		super(mapDriver);
		driver = mapDriver;
	}

	public void openRegitserPage() {
		openPageUrl(RegisterPageUI.REGISTER_PAGE_URL);
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(HomePageUI.WELCOME_MESSAGE);
		return isElementDisplayed(HomePageUI.WELCOME_MESSAGE);
	}

	public String getHomePageUrll() {
		return getPageUrl();
	}
}
