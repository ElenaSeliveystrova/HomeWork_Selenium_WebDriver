package greenCity.locators;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ForgotPasswordElements {
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]//div[@class=\"forgot-wrapper-ubs ng-star-inserted\"]/a")
    private WebElement forgotPasswordButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog\")]/app-auth-modal//app-restore-password/div/h1")
    private WebElement troubleText;
    @FindBy(xpath = "//*[contains(@id, \"mat-dialog-\")]/app-auth-modal//app-restore-password/div/h2")
    private WebElement troubleDetailsText;
    @FindBy(xpath = "//*/snack-bar-container/simple-snack-bar/span")
    private WebElement sendLinkMessage;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/form/div/div")
    private WebElement errorSubmitLink;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/div/p/a")
    private WebElement backToSignInButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/div/p")
    private WebElement backToSignInText;
    @FindBy(css = "a.close-modal-window")
    private WebElement closeButton;
    public ForgotPasswordElements(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
