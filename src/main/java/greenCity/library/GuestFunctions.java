package greenCity.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GuestFunctions {
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "app-ubs .ubs-header-sing-in-img")
//    @FindBy(css = "img.ubs-header-sing-in-img")
    private WebElement signInButton;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog\")]//form/button")
    private WebElement submitLoginLinkButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]//div[@class=\"forgot-wrapper-ubs ng-star-inserted\"]/a")
    private WebElement forgotPasswordButton;
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Wait<WebDriver> wait;

    public GuestFunctions(WebDriver driver, WebDriverWait webDriverWait, Wait<WebDriver> wait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public void fillUserData(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.wrapper")));
    }

    public void fillForgotPasswordForm(String email) {
        clickSignInButton();
        forgotPasswordButton.click();
        emailInput.sendKeys(email);
        submitLoginLinkButton.click();
    }

    public void checkMessageFromForgotPasswordForm(String email, WebElement element, String errorMessage) {
        fillForgotPasswordForm(email);
        this.webDriverWait.until(ExpectedConditions.visibilityOf(element));
        assertThat(element.getText(), is(errorMessage));
    }

}
