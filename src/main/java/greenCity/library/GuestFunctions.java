package greenCity.library;

import greenCity.locators.ForgotPasswordElements;
import greenCity.locators.SignInElements;
import greenCity.test.TestRunner;
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
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Wait<WebDriver> wait;
    private static SignInElements signInElements;
    private static ForgotPasswordElements forgotPasswordElements;

    public GuestFunctions(WebDriver driver, WebDriverWait webDriverWait, Wait<WebDriver> wait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
        signInElements = new SignInElements(driver);
        forgotPasswordElements = new ForgotPasswordElements(driver);
    }

    public void fillUserData(String email, String password) {
        forgotPasswordElements.getEmailInput().sendKeys(email);
        forgotPasswordElements.getPasswordInput().sendKeys(password);
    }

    public void clickSignInButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInElements.signInButton));
        signInElements.signInButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.wrapper")));
    }

    public void fillForgotPasswordForm(String email) {
        clickSignInButton();
        forgotPasswordElements.getForgotPasswordButton().click();
        forgotPasswordElements.getEmailInput().sendKeys(email);
        forgotPasswordElements.getSubmitLoginLinkButton().click();
    }

    public void checkMessageFromForgotPasswordForm(String email, WebElement element, String errorMessage) {
        fillForgotPasswordForm(email);
        this.webDriverWait.until(ExpectedConditions.visibilityOf(element));
        assertThat(element.getText(), is(errorMessage));
    }

}
