import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.BaseDriver;

@CucumberOptions(
        features = "classpath:features",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report.json"})
public class RunCukesTest extends BaseDriver {

}
