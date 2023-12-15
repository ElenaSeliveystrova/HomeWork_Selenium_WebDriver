package greenCity.test;

import greenCity.data.User;
import greenCity.data.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignInTest extends TestRunner {

    @FindBy(css = ".ng-star-inserted > h1")
    private WebElement welcomeText;
    @FindBy(css = ".wrapper h2")
    private WebElement signInDetailsText;
    @FindBy(css = "label[for=\"email\"]")
    private WebElement emailLabel;
    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;
    @FindBy(xpath = "//*[@id=\"header_user-wrp\"]/ul/li[@aria-label=\"sign-out\"] [@class=\"drop-down-item\"]")
    private WebElement signOutButton;
    @FindBy(xpath = "//*[@id=\"pass-err-msg\"]/app-error/div")
    private WebElement errorPassword;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]/li")
    private WebElement headerUser;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]")
    private WebElement headerUserButton;
    @FindBy(css = "a.close-modal-window")
    private WebElement closeButton;
    @FindBy(xpath = "//app-auth-modal/div/div/div[2]/div/app-sign-in/form/label[2]")
    private WebElement passwordLabel;
    @FindBy(css = "div.alert-general-error.ng-star-inserted")
    private WebElement errorSubmit;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p")
    private WebElement redirectSignUpText;

    //    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p/a")
//    private WebElement redirectSignUpButton;
    @FindBy(css = "h1.title-text")
    private WebElement helloText;
    @FindBy(xpath = "//*/app-auth-modal//app-sign-up/h2")
    private WebElement signUpDetailsText;


    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usersData.csv")
    public void signInTest(String email, String password, String userName) {
        guestFunctions.clickSignInButton();

        assertThat(welcomeText.getText(), is("Welcome back!"));
        assertThat(signInDetailsText.getText(), is("Please enter your details to sign in."));
        assertThat(emailLabel.getText(), is("Email"));

        guestFunctions.fillUserData(email, password);

        signInSubmitButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerUser));
        assertThat(headerUser.getText(), is(userName));
        headerUserButton.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerUser));
        headerUser.isSelected();
        signOutButton.click();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "samplestesgreencity@gmail",
            "samplestesgreencity.com"
    })
    public void signInNotValidEmail(String email) {
        guestFunctions.clickSignInButton();
        guestFunctions.fillUserData(email, "uT346^^^erw");
        webDriverWait.until(ExpectedConditions.visibilityOf(errorEmail));
        assertThat(errorEmail.getText(), is("Please check if the email is written correctly"));
        closeButton.click();
    }

//    @ParameterizedTest
//    @CsvSource({
//            "testerforapp2023@gmail.com, 1234, Password must be at least 8 characters long without spaces",
//            "testerforapp2023@gmail.com, 123456789123456789123456789123456789, Password must be less than 20 characters long without spaces."
//    })
//    public void signInNotValidPassword(String email, String password, String message) {
//        guestFunctions.clickSignInButton();
//        guestFunctions.fillUserData(email, password);
//        passwordLabel.click();
//        assertThat(errorPassword.getText(), is(message));
//        closeButton.click();
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "testerforapp2023@gmail.com, 12345678",
//            "testerforaApp2023@gmail.com, nehmys-hipbic-5jadcU"
//    })
//    public void signInIncorrectData(String email, String password) {
//        guestFunctions.clickSignInButton();
//        guestFunctions.fillUserData(email,password);
//        signInSubmitButton.click();
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert-general-error.ng-star-inserted")));
//        assertThat(errorSubmit.getText(), is("Bad email or password"));
//        closeButton.click();
//    }

    //
    @ParameterizedTest
    @MethodSource("userDataProvider")
    public void incorrectUserDataTest(User user, String message) {
        guestFunctions.clickSignInButton();
        guestFunctions.fillUserData(user.getEmail(), user.getPassword());
        boolean submitButtonEnabled = signInSubmitButton.isEnabled();
        if (submitButtonEnabled) {
            signInSubmitButton.click();
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert-general-error.ng-star-inserted")));
            assertThat(errorSubmit.getText(), is(message));
        } else {
            passwordLabel.click();
            assertThat(errorPassword.getText(), is(message));
        }
        closeButton.click();

    }

    @Test
    public void redirectSignUp() {
        guestFunctions.clickSignInButton();
        assertThat(redirectSignUpText.getText(), is("Don't have an account yet? Sign up"));
        //        redirectSignUpButton.click();
        js.executeScript("document.querySelector('a.ubs-link').click()");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.title-text")));
        assertThat(helloText.getText(), is("Hello!"));
        assertThat(signUpDetailsText.getText(), is("Please enter your details to sign up."));
        closeButton.click();
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
