
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class MyStepdefs {
    @Given("I wait for {int} second")
    public void iWaitForSecond(int arg0) throws InterruptedException {
        Thread.sleep(arg0);
    }

    @Then("^I assert (.+) something$")
    public void iAssertTrue(boolean flag) {
        Assert.assertTrue(flag);
    }
}
