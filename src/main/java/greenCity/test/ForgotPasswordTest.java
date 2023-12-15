package greenCity.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ForgotPasswordTest extends TestRunner {
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

    @Test
    public void checkFormForgotPasswordTest() {
        guestFunctions.clickSignInButton();
        forgotPasswordButton.click();
        assertThat(troubleText.getText(), is("Trouble singing in?"));
        assertThat(troubleDetailsText.getText(), is("Enter your email address and we'll send you a link to regain access to your account."));
        assertThat(backToSignInText.getText(), is("Remember your password? Back to Sign in"));
        // wait
        backToSignInButton.click();
        //wait
        closeButton.click();
    }

    @ParameterizedTest
    @MethodSource(value = "emailProvider")
    @Order(1)
    public void forgotPasswordTest(String email) {
        guestFunctions.checkMessageFromForgotPasswordForm(email, sendLinkMessage,
                "Restore password link was sent to your email address.");
    }

    @ParameterizedTest()
    @MethodSource(value = "emailProvider")
    @Order(2)
    public void forgotPasswordLinkAlreadySentTest(String email) {
        guestFunctions.checkMessageFromForgotPasswordForm(email, errorSubmitLink,
                "Password restore link already sent, please check your email: " + email);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }

    private static Stream<String> emailProvider() {
        return Stream.of(
//                "magicnimfa@gmail.com",
//                "testerforapp2023@gmail.com"
//                "cowafo7557@bustayes.com",
                "xapajoy635@frandin.com"

        );
    }

}
