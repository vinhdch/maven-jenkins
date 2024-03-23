package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	// public static BasePage getBasePage() {
	// return new BasePage();
	// }

	public void openPageUrl(String pageUrl) {
		driver.get(pageUrl);
	}

	public String openPageTitle() {
		return driver.getTitle();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public String openPageSourceCodel() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
		;
	}

	public Set<Cookie> getAllCookies() {
		return driver.manage().getCookies();
	}

	public String getElementValueByJS(String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");

	}

	public void setCookies(Set<Cookie> cookies) {

		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(GlobalConstants.SHORT_TIMEOUT);
	}

	protected Alert waitForAlertPresense() {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert() {
		waitForAlertPresense().accept();
	}

	protected void cancelAlert() {
		waitForAlertPresense().dismiss();
	}

	protected String getAlertText() {
		return waitForAlertPresense().getText();
	}

	protected void sendkeyToAlert(String textValue) {
		waitForAlertPresense().sendKeys(textValue);
	}

	protected void switchToWindowById(String windowId) {
		Set<String> allWindowsIds = driver.getWindowHandles();
		for (String id : allWindowsIds) {
			if (!id.equals(windowId)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByTtile(String expectedTtitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {

			driver.switchTo().window(id);

			String actualTitle = driver.getTitle();

			if (actualTitle.equals(expectedTtitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public WebElement getWebElement(String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	// private By getByXpath(String locatorType) {
	// return By.xpath(locatorType);
	// }

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else {
			throw new RuntimeException("Locator is not supported");
		}
		return by;
	}

	protected void clickToElement(String locatorType) {
		getWebElement(locatorType).click();
	}

	private String getDynamicXpath(String locatorType, String... values) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		return locatorType;
	}

	protected void clickToElement(String locatorType, String... dynamicValues) {
		getWebElement(getDynamicXpath(locatorType, dynamicValues)).click();
	}

	protected void sendkeyToElement(String locatorType, String textValue) {
		WebElement element = getWebElement(locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void clearValueInElementByDeleteKey(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	protected void sendkeyToElement(String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected String getElementText(String locatorType) {
		return getWebElement(locatorType).getText();
	}

	protected String getElementText(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	protected void selectItemInDefaultDropdown(String locatorType, String textValue) {
		Select select = new Select(getWebElement(locatorType));
		select.selectByVisibleText(textValue);
	}

	protected void selectItemInDefaultDropdown(String locatorType, String textValue, String... dynamicValues) {
		Select select = new Select(getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textValue);
	}

	protected String getSelectedItemInDefaultDropdown(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected Boolean isDropdownMultiple(String locatorType) {
		Select select = new Select(getWebElement(locatorType));
		return select.isMultiple();
	}

	protected void selectItemOnCustomDropdown(String parentXpath, String childXpath, String expectedItems) {
		getWebElement(parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

		List<WebElement> allItemsElements = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItemsElements) {
			if (item.getText().trim().equals(expectedItems)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeSecond) {
		try {
			Thread.sleep(timeSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(String locatorType, String arrtibuteName) {
		return getWebElement(locatorType).getAttribute(arrtibuteName);
	}

	protected String getElementAttribute(String locatorType, String arrtibuteName, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).getAttribute(arrtibuteName);
	}

	protected String getCssValue(String locatorType, String propertyName) {
		return getWebElement(locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(String locatorType) {
		return getListWebElement(locatorType).size();
	}

	protected int getElementSize(String locatorType, String... dynamicValues) {
		return getListWebElement(getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxOrRadio(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(String locatorType) {
		WebElement element = getWebElement(locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	protected Boolean isElementDisplayed(String locatorType) {
		return getWebElement(locatorType).isDisplayed();
	}

	protected Boolean isElementDisplayed(String locatorType, String... dynamicValues) {
		return getWebElement(getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(String locator) {
		overrideGlobalTimeout(GlobalConstants.SHORT_TIMEOUT);

		List<WebElement> elements = getListWebElement(locator);

		overrideGlobalTimeout(GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public void overrideGlobalTimeout(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected Boolean isElementEnabled(String locatorType) {
		return getWebElement(locatorType).isEnabled();
	}

	protected Boolean isElementSelected(String locatorType) {
		return getWebElement(locatorType).isSelected();
	}

	protected void switchToFrameIdrame(String locatorType) {
		driver.switchTo().frame(getWebElement(locatorType));
	}

	protected void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locatorType)).perform();
	}

	protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(locatorType));
	}

	protected void scrollToElement(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
	}

	protected void removeAttributeInDOM(String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locatorType));
	}

	protected void removeAttributeInDOM(String locatorType, String attributeRemove, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected WebElement getShahowDOM(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = (WebElement) jsExecutor.executeAsyncScript("return arguments[0].shadowRoot", getWebElement(locatorType));
		return element;

	}

	protected String getElementValidationMessage(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locatorType));
	}

	protected boolean isImageLoaded(String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(getDynamicXpath(locatorType, dynamicValues)));
		return status;
		// if (status) {
		// return true;
		// } else {
		// return false;
		// }
	}

	protected void waitForElementVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsVisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsInvisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsInisible(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementsClickable(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForAllElementsVisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementsInvisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForAllElementsInisible(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(locatorType)));
	}

	protected void waitForElementsClickable(String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementsClickable(String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void uploadMulipleFiles(String... fileNames) {

		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(GlobalConstants.UPLOAD_FILE_JQUERY).sendKeys(fullFileName);

	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
