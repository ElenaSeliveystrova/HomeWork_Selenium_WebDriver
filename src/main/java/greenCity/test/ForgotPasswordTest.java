package greenCity.test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ForgotPasswordTest extends TestRunner {

    @Test
    public void checkFormForgotPasswordTest() {
        guestFunctions.clickSignInButton();
        forgotPasswordElements.getForgotPasswordButton().click();
        assertThat(forgotPasswordElements.getTroubleText().getText(), is("Trouble singing in?"));
        assertThat(forgotPasswordElements.getTroubleDetailsText().getText(), is("Enter your email address and we'll send you a link to regain access to your account."));
        assertThat(forgotPasswordElements.getBackToSignInText().getText(), is("Remember your password? Back to Sign in"));
        // wait
        forgotPasswordElements.getBackToSignInButton().click();
    }

    @ParameterizedTest
    @MethodSource(value = "emailProvider")
    @Order(1)
    public void forgotPasswordTest(String email) {
        guestFunctions.checkMessageFromForgotPasswordForm(email, forgotPasswordElements.getSendLinkMessage(),
                "Restore password link was sent to your email address.");
    }

    @ParameterizedTest()
    @MethodSource(value = "emailProvider")
    @Order(2)
    public void forgotPasswordLinkAlreadySentTest(String email) {
        guestFunctions.checkMessageFromForgotPasswordForm(email, forgotPasswordElements.getErrorSubmitLink(),
                "Password restore link already sent, please check your email: " + email);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(forgotPasswordElements.getCloseButton()));
    }

    private static Stream<String> emailProvider() {
        return Stream.of(
//                "magicnimfa@gmail.com",
                "testerforapp2023@gmail.com"
//                "cowafo7557@bustayes.com",
//                "xapajoy635@frandin.com"

        );
    }

}
