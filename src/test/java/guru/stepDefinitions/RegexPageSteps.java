package guru.stepDefinitions;

import cucumber.api.java.en.Given;

public class RegexPageSteps {

	@Given("^I input (first|second|third) account ID$")
	public void iInputThirdAccountID(String account) {
		if (account.equals("first")) {
			// ....
		} else if (account.equals("second")) {
			// ....
		} else {
			// ....
		}
	}

	@Given("^I (?:transfer|withdraw) to \"([^\"]*)\" USD$")
	public void iTransferToUSD(String amount) {
		// ..
	}
}
