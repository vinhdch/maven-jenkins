package guru.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.DataHelpers;
import commons.PageGeneraterManager;
import commons.commonBaseObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class CommonPageSteps {

	WebDriver driver;
	String email;
	DataHelpers dataHelpers;
	commonBaseObject commonBaseObject;

	public CommonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonBaseObject = PageGeneraterManager.getCommonPageObject(driver);
		email = dataHelpers.getEmail();
	}

	@Given("^Open New customer page$")
	public void openNewCustomerPage(String pageName) {
		commonBaseObject.clickToDynamicLink(pageName);
	}

	@When("^Click Submit button$")
	public void clickSubmitButton() {

	}

	@Then("^Verify to successful message$")
	public void verifyToSuccessfulMessage(String expectedMessage) {
		Assert.assertTrue(commonBaseObject.isDynamicMessageDisplayed(expectedMessage));
	}

	@Then("^The correct input value is displayed with value \"([^\"]*)\"$")
	public void theCorrectInputValueIsDisplayedWithValue(String rowName, String expectedValue) {
		if (rowName.equals("E-mail")) {
			expectedValue = email;
		}
		Assert.assertEquals(commonBaseObject.getDynamicValueByRowName(rowName), expectedValue);
	}

	@When("^Click to \"([^\"]*)\" radio button$")
	public void clickToRadioButton(String fieldName, String radioButtonValue) {
		commonBaseObject.clickToDynamicRadioButton(radioButtonValue);
	}

	@When("^Input to customer name textbox with value \"([^\"]*)\"$")
	public void inputToCustomerNameTextboxWithValue(String fieldName, String inputValue) {
		commonBaseObject.inputToDynamicTextbox(fieldName, inputValue);
	}

	@When("^Input to \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
	public void inputToTextareaWithValue(String fieldName, String inputValue) {
		commonBaseObject.inputToDynamicTextarea(fieldName, inputValue);
	}

	@Given("^Open New customer page with value \"([^\"]*)\"$")
	public void openNewCustomerPageWithValue(String arg1) {

	}

}
