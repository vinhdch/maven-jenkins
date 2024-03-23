package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneraterManager;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver mapDriver) {
		super(mapDriver);
		driver = mapDriver;
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public RegisterPageObject clickToSubmitButton() {
		waitForElementVisible(RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(RegisterPageUI.SUBMIT_BUTTON);
		return PageGeneraterManager.getRegisterPageObject(driver);
	}

	public String getUserIDText() {
		waitForAllElementsVisible(RegisterPageUI.USER_ID_TEXT);
		return getElementText(RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {
		waitForAllElementsVisible(RegisterPageUI.PASSWORD_TEXT);
		return getElementText(RegisterPageUI.PASSWORD_TEXT);
	}

	public HomePageObject openLoginPageObject(String loginPageUrl) {
		openPageUrl(RegisterPageUI.REGISTER_PAGE_URL);
		return PageGeneraterManager.getHomePageObject(driver);
	}

}
