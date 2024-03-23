package commons;

import org.openqa.selenium.WebDriver;

import pageUI.CommonBankPageUI;

public class commonBaseObject extends BasePage {

	WebDriver driver;

	public commonBaseObject(WebDriver driver) {
		super(driver);
		driver = this.driver;
	}

	public void inputToDynamicTextbox(String fieldName, String inputValue) {
		waitForElementVisible(CommonBankPageUI.DYNAMIC_TEXBOX, fieldName);
		if (fieldName.equals("dob")) {
			removeAttributeInDOM(CommonBankPageUI.DYNAMIC_TEXBOX, "type", fieldName);
			sleepInSecond(1);
		}
		sendkeyToElement(CommonBankPageUI.DYNAMIC_TEXBOX, inputValue, fieldName);
	}

	public void inputToDynamicTextarea(String fieldName, String inputValue) {
		waitForElementsClickable(CommonBankPageUI.DYNAMIC_TEXAREA, fieldName);
		sendkeyToElement(CommonBankPageUI.DYNAMIC_TEXAREA, inputValue, fieldName);
	}

	public void clickToDynamicButton(String buttonValue) {
		waitForElementsClickable(CommonBankPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(CommonBankPageUI.DYNAMIC_BUTTON, buttonValue);
	}

	public void clickToDynamicRadioButton(String radioButtonValue) {
		waitForElementsClickable(CommonBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
		clickToElement(CommonBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
	}

	public void clickToDynamicLink(String link) {
		waitForElementsClickable(CommonBankPageUI.DYNAMIC_LINK, link);
		clickToElement(CommonBankPageUI.DYNAMIC_LINK, link);
	}

	public boolean isDynamicMessageDisplayed(String message) {
		waitForElementVisible(CommonBankPageUI.DYNAMIC_MESSAGE, message);
		return isElementDisplayed(CommonBankPageUI.DYNAMIC_MESSAGE, message);
	}

	public String getDynamicValueByRowName(String rowName) {
		waitForElementVisible(CommonBankPageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
		return getElementText(CommonBankPageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
	}
}
