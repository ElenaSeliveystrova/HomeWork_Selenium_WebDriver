package greenCity.test;

import greenCity.data.User;
import greenCity.data.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.*;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignInTest extends TestRunner {


    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usersData.csv")
    public void signInTest(String email, String password, String userName) {
        guestFunctions.clickSignInButton();

        assertThat(signInElements.welcomeText.getText(), is("Welcome back!"));
        assertThat(signInElements.signInDetailsText.getText(), is("Please enter your details to sign in."));
        assertThat(signInElements.emailLabel.getText(), is("Email"));

        guestFunctions.fillUserData(email, password);

        signInElements.signInSubmitButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInElements.headerUser));
        assertThat(signInElements.headerUser.getText(), is(userName));
        signInElements.headerUserButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(signInElements.headerUser));
        signInElements.headerUser.isSelected();
        signInElements.signOutButton.click();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "samplestesgreencity@gmail",
            "samplestesgreencity.com"
    })
    public void signInNotValidEmail(String email) {
        guestFunctions.clickSignInButton();
        guestFunctions.fillUserData(email, "uT346^^^erw");
        webDriverWait.until(ExpectedConditions.visibilityOf(signInElements.errorEmail));
        assertThat(signInElements.errorEmail.getText(), is("Please check if the email is written correctly"));
        signInElements.closeButton.click();
    }

    @ParameterizedTest
    @MethodSource("userDataProvider")
    public void incorrectUserDataTest(User user, String message) {
        guestFunctions.clickSignInButton();
        guestFunctions.fillUserData(user.getEmail(), user.getPassword());
        boolean submitButtonEnabled = signInElements.signInSubmitButton.isEnabled();
        if (submitButtonEnabled) {
            signInElements.signInSubmitButton.click();
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert-general-error.ng-star-inserted")));
            assertThat(signInElements.errorSubmit.getText(), is(message));
        } else {
            signInElements.passwordLabel.click();
            assertThat(signInElements.errorPassword.getText(), is(message));
        }
        signInElements.closeButton.click();

    }

    @Test
    public void redirectSignUp() {
        guestFunctions.clickSignInButton();
        assertThat(signInElements.redirectSignUpText.getText(), is("Don't have an account yet? Sign up"));
        //        redirectSignUpButton.click();
        localStorageJS.clickRedirectSignUpButton();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.title-text")));
        assertThat(signInElements.helloText.getText(), is("Hello!"));
        assertThat(signInElements.signUpDetailsText.getText(), is("Please enter your details to sign up."));
        signInElements.closeButton.click();
    }

    private static Stream<Arguments> userDataProvider() {
        return Stream.of(
                Arguments.of(UserRepository.getInvalidUser(),
                        "Password must be at least 8 characters long without spaces"),
                Arguments.of(new User("testerforapp2023@gmail.com", "123456789123456789123456789123456789", null),
                        "Password must be less than 20 characters long without spaces."),
                Arguments.of(new User("testerforapp2023@gmail.com", "12345678", null),
                        "Bad email or password"),
                Arguments.of(new User("testerforaApp2023@gmail.com", "nehmys-hipbic-5jadcU", null),
                        "Bad email or password")
        );
    }

}
