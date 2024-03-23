package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/guru/features", glue = "guru.stepDefinitions", monochrome = true, plugin = { "pretty", "html:target/site/cucumber-report-default",
		"json:target/site/cucumber_guru.json" }, snippets = SnippetType.CAMELCASE, tags = { "@register" })
public class BankTestRunners {

}
