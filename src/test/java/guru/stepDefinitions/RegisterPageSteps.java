package guru.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.DataHelpers;
import commons.PageGeneraterManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.RegisterPageObject;

public class RegisterPageSteps {
	WebDriver driver;
	String username, password;
	RegisterPageObject registerPageObject;
	DataHelpers dataHelpers;
	TestContext testContext;

	public RegisterPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		registerPageObject = PageGeneraterManager.getRegisterPageObject(driver);
		dataHelpers = DataHelpers.getDataHelpers();
	}

	@When("^Input to email textbox$")
	public void inputToEmailTextbox() {
		registerPageObject.inputToEmailTextbox(dataHelpers.getEmail());
	}

	@When("^Click to submit$")
	public void clickToSubmit() {
		registerPageObject.clickToSubmitButton();
	}

	@Then("^Get username and password$")
	public void getUsernameAndPassword() {
		// 2 ways to share data: declare static variable (share giua cac scenario) or using testContext (share giua cac class)
		testContext.getDataContext().setContext(Context.USER_ID, registerPageObject.getUserIDText());
		testContext.getDataContext().setContext(Context.PASSWORD, registerPageObject.getPasswordText());
	}

	@Then("^Back to login page$")
	public void backToHomePage() {
		// dung thu vien picoContainer de share data trong cucumber framework
		// do getContext nhan Object (bat ky loai data type nao) => bat buoc phai ep kieu: getContext() or truc tiep tai ham dang sd
		registerPageObject.openLoginPageObject((String) testContext.getDataContext().getContext(Context.LOGIN_URL));

	}

}
