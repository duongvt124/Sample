package lgv.automation.page.desktop.opsTool;

import lgv.automation.util.Log;
import lgv.automation.util.desktop.UIComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage{


    WebDriver webDriver;

    @FindBy(how = How.ID, using = "email")
    private List<WebElement> txtEmail;

    @FindBy(how = How.ID, using = "password")
    private List<WebElement> txtPassword;

    @FindBy(how = How.XPATH, using = "//input[@type='checkbox']")
    private List<WebElement> chkRememberMe;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private List<WebElement> btnSubmit;

    public LoginPage(WebDriver webDriver) {

        this.webDriver = webDriver;

        //This initElements method will create all WebElements
        PageFactory.initElements(webDriver, this);
    }

    public LoginPage fillEmail(String email) {

        UIComponent.fillText(webDriver, txtEmail.get(0), email);
        Log.debug("Fill email : " + email + " success");

        return this;
    }

    public LoginPage fillPassword(String password) {

        UIComponent.fillText(webDriver, txtPassword.get(0), password);
        Log.debug("Fill password : " + password + " success");

        return this;
    }

    public LoginPage submitLogin() {

        UIComponent.clickOnElement(webDriver, btnSubmit.get(0));
        Log.debug("Click on Dang nhap button success");

        return this;
    }
}
