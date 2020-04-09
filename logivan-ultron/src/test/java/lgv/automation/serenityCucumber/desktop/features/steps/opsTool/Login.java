package lgv.automation.serenityCucumber.desktop.features.steps.opsTool;

import io.cucumber.java.en.Given;
import lgv.automation.steps.desktop.OpsToolSteps;
import lgv.automation.util.desktop.Config;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class Login {

    @Steps
    OpsToolSteps opsToolSteps;

    @Given("ops manager should be able to go to ops tool login page by url")
    public void go_to_login_page_by_url() {

        opsToolSteps.openLoginPage();
    }

    @Given("ops manager should be able to login ops tool with email = {string} and password = {string}")
    public void login_ops_tool(String email, String password) {

        opsToolSteps.login(email, password);
    }

}
