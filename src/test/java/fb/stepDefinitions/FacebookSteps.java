package fb.stepDefinitions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	// truong hop muon apply before/after cho tag specific, thi them tag vao sau before/after
	// neu chay before/after cho all feature, thi k can them tag vao sau before/after
	@Before("@login")
	@Given("^Open facebook application$")
	public void openFacebookApplication() {
		WebDriverManager.chromedriver().avoidResolutionCache().setup();
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Then("^Input to Username textbox$")
	public void inputToUsernameTextbox() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("vinhdch@gmail.com");
	}

	@Then("^Input to Password textbox$")
	public void inputToPasswordTextbox() {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("vinhdch@gmail.com");
	}

	@Then("^Click to submit button$")
	public void clickToSubmitButton() {
		driver.findElement(By.name("login")).click();
	}

	@After("@login")
	@Then("^Close application$")
	public void closeApplication() {
		driver.quit();
	}

	// @Then("^Input to Username textbox with \"([^\"]*)\"$")
	// public void inputToUsernameTextboxWith(String email) {
	// driver.findElement(By.id("email")).clear();
	// driver.findElement(By.id("email")).sendKeys(email);
	// }
	//
	// @Then("^Input to Password textbox with \"([^\"]*)\"$")
	// public void inputToPasswordTextboxWith(String pass) {
	// driver.findElement(By.id("pass")).clear();
	// driver.findElement(By.id("pass")).sendKeys(pass);
	// }

	@Then("^Input to Username and password$")
	public void inputToUsernameAndPassword(DataTable table) {

		// with 1 row
		// List<Map<String, String>> customer = table.asMaps(String.class, String.class);
		// driver.findElement(By.id("email")).clear();
		// driver.findElement(By.id("email")).sendKeys(customer.get(0).get("Email"));
		//
		// driver.findElement(By.id("pass")).clear();
		// driver.findElement(By.id("pass")).sendKeys(customer.get(0).get("Password"));

		// with multiple rows
		for (Map<String, String> loginInfo : table.asMaps(String.class, String.class)) {

			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys(loginInfo.get("Email"));

			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("pass")).sendKeys(loginInfo.get("Password"));
		}

	}

}
