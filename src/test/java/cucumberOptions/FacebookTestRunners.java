package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/fb/features", glue = "fb.stepDefinitions", monochrome = true, plugin = { "pretty", "html:target/site/cucumber-report-default",
		"json:target/site/cucumber_fb.json" }, snippets = SnippetType.CAMELCASE, tags = { "@login" })
public class FacebookTestRunners {

}
