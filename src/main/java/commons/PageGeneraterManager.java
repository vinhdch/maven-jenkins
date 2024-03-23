package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class PageGeneraterManager {

	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static commonBaseObject getCommonPageObject(WebDriver driver) {
		return new commonBaseObject(driver);
	}

}
