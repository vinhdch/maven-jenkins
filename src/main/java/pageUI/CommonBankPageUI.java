package pageUI;

public class CommonBankPageUI {

	public static String DYNAMIC_TEXBOX = "xpath=//input[@name='%s']";
	public static String DYNAMIC_TEXAREA = "xpath=//textarea[@name='%s']";
	public static String DYNAMIC_RADIO_BUTTON = "xpath=//input[@type='radio' @value = '%s']";
	public static String DYNAMIC_LINK = "xpath=//a[text()='%s']";
	public static String DYNAMIC_BUTTON = "xpath=//input[@value='%s']";
	public static String DYNAMIC_MESSAGE = "xpath=//p[@class='heading3 and text()='%s']";
	public static String DYNAMIC_VALUE_BY_ROW_NAME = "xpath=//td[contains(text()), '%s']/following-sibling::td]";
}
