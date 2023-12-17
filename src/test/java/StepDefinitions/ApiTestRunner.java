package StepDefinitions;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "StepDefinitions", // Replace with your actual step definitions package
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}