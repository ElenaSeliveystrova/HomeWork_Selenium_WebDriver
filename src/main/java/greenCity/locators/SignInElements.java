package greenCity.locators;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class SignInElements {
    @FindBy(css = ".ng-star-inserted > h1")
    public WebElement welcomeText;
    @FindBy(css = ".wrapper h2")
    public WebElement signInDetailsText;
    @FindBy(css = "label[for=\"email\"]")
    public WebElement emailLabel;
    @FindBy(css = "app-ubs .ubs-header-sing-in-img")
//    @FindBy(css = "img.ubs-header-sing-in-img")
    public WebElement signInButton;
    @FindBy(css = ".ubsStyle")
    public WebElement signInSubmitButton;
    @FindBy(xpath = "//*[@id=\"header_user-wrp\"]/ul/li[@aria-label=\"sign-out\"] [@class=\"drop-down-item\"]")
    public WebElement signOutButton;
    @FindBy(xpath = "//*[@id=\"pass-err-msg\"]/app-error/div")
    public WebElement errorPassword;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    public WebElement errorEmail;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]/li")
    public WebElement headerUser;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]")
    public WebElement headerUserButton;
    @FindBy(css = "a.close-modal-window")
    public WebElement closeButton;
    @FindBy(css = "a.close-modal-window")
    public List<WebElement> listCloseButton;
    @FindBy(xpath = "//app-auth-modal/div/div/div[2]/div/app-sign-in/form/label[2]")
    public WebElement passwordLabel;
    @FindBy(css = "div.alert-general-error.ng-star-inserted")
    public WebElement errorSubmit;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p")
    public WebElement redirectSignUpText;

    //    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p/a")
//    private WebElement redirectSignUpButton;
    @FindBy(css = "h1.title-text")
    public WebElement helloText;
    @FindBy(xpath = "//*/app-auth-modal//app-sign-up/h2")
    public WebElement signUpDetailsText;

    public SignInElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
