package lgv.automation.serenityCucumber.api.features.steps.driver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lgv.automation.model.api.Driver;
import lgv.automation.serenityJunit.features.api.TestSuiteMaster;
import lgv.automation.steps.api.DriverSteps;
import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class SignIn extends TestSuiteMaster {

    String phoneNumber = Config.newDriverPhoneNumber;
    String passWord = Config.password;

    @Steps
    DriverSteps driverSteps;

    @When("new driver should be able to sign in driver app")
    public void new_driver_should_able_to_sign_in_on_driver_app(){

        driverSteps.shouldBeAbleToSignIn(phoneNumber, passWord);
    }

    @Given("driver should be able to sign in driver app")
    public void driver_should_able_to_sign_in_on_driver_app(){

        driverSteps.shouldBeAbleToSignIn(Config.phoneNumber, passWord);
    }

    @Given("driver {int} should be able to sign in driver app")
    public void driver_should_able_to_sign_in_on_driver_app(int driverNumber){

//        driverSteps.shouldBeAbleToSignIn(CommonMethod.getDriverPhoneNumberByIndex(driverIndex), passWord);
        Driver driver = Config.listDriver.get(driverNumber - 1);
        driverSteps.shouldBeAbleToSignIn(driver.getPhoneNumber(), passWord, driverNumber - 1);
    }

    @Given("driver with phone number = {string} should be able to sign in driver app")
    public void driver_should_able_to_sign_in_on_driver_app(String phoneNumber){

        driverSteps.shouldBeAbleToSignIn(phoneNumber, passWord);
    }

    @Test
    public void signInSuccess(){

        Log.highlight("--------------- BEGIN TO TEST DRIVER PRICE LOOKUP API! ---------------");

        driverSteps.shouldBeAbleToSignIn(Config.phoneNumber, Config.password);

        Log.highlight("--------------- FINISH DRIVER PRICE LOOKUP API TESTING! ---------------");
    }

}
