import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignInTest {
    @FindBy(css = "app-header:nth-child(1) .ubs-header-sing-in-img")
    private WebElement signInButton;
    @FindBy(css = ".ng-star-inserted > h1")
    private WebElement welcomeText;
    @FindBy(css = "h2:nth-child(2)")
    private WebElement signInDetailsText;
    @FindBy(css = "label:nth-child(1)")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;
    @FindBy(xpath = "//*[@id=\"header_user-wrp\"]/ul/li[2] [@class=\"drop-down-item\"]")
    private WebElement signOutButton;
    @FindBy(css = ".mat-simple-snackbar > span")
    private WebElement result;
    @FindBy(css = ".alert-general-error")
    private WebElement errorMessage;
    @FindBy(xpath = "//*[@id=\"pass-err-msg\"]/app-error/div")
    private WebElement errorPassword;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]/li")
    private WebElement headerUser;
    @FindBy(xpath = "//app-main//app-ubs/app-header/header//ul[@id=\"header_user-wrp\"]")
    private WebElement headerUserButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//div[@class=\"right-side\"]/a")
//    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal/div/div/div[2]/a")
    private WebElement closeButton;
    @FindBy(xpath = "//app-auth-modal/div/div/div[2]/div/app-sign-in/form/label[2]")
    private WebElement passwordLabel;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]//div[@class=\"forgot-wrapper-ubs ng-star-inserted\"]/a")
    private WebElement forgotPasswordButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog\")]/app-auth-modal//app-restore-password/div/h1")
    private WebElement troubleText;
    @FindBy(xpath = "//*[contains(@id, \"mat-dialog-\")]/app-auth-modal//app-restore-password/div/h2")
    private WebElement troubleDetailsText;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog\")]//form/button")
    private WebElement submitLoginLinkButton;
    @FindBy(xpath = "//*/snack-bar-container/simple-snack-bar/span")
    private WebElement sendLinkMessage;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/form/div/div")
    private WebElement errorSubmitLink;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/div/p")
    private WebElement backToSignInText;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-restore-password/div/div/p/a")
    private WebElement backToSignInButton;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p")
    private WebElement redirectSignUpText;
    @FindBy(xpath = "//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/div[@class=\"missing-account\"]/p/a")
    private WebElement redirectSignUpButton;
    @FindBy(xpath = "//*/app-auth-modal//app-sign-up/h1")
    private WebElement helloText;
    @FindBy(xpath = "//*/app-auth-modal//app-sign-up/h2")
    private WebElement signUpDetailsText;

//    @FindBy(xpath = "//*/app-google-btn/button")
//    private WebElement signInGoogleButton;
//
//    @FindBy(xpath = "//*[@id=\"picker-title\"]/span")
//    private WebElement signInGoogleText;

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.greencity.social/");
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    @Test
    public void verifyTitle() {
        assertThat(driver.getTitle(), is("GreenCity"));
    }

    @ParameterizedTest
    @CsvSource({
            "magicnimfa@gmail.com, qypsaw-1hIvsy-tikcaq, SeliveystrovaElenna",
            "testerforapp2023@gmail.com, nehmys-hipbic-5jadcU, RimaUkraine",
            "cowafo7557@bustayes.com, zyzkug-dacxoq-2hoswY, KiraNightly"
    })
    public void signInTest(String email, String password, String userName) throws InterruptedException {
        signInButton.click();
        Thread.sleep(3000);
        MatcherAssert.assertThat(welcomeText.getText(), CoreMatchers.is("Welcome back!"));
        MatcherAssert.assertThat(signInDetailsText.getText(), CoreMatchers.is("Please enter your details to sign in."));
        MatcherAssert.assertThat(emailLabel.getText(), CoreMatchers.is("Email"));
        emailInput.sendKeys(email);
        MatcherAssert.assertThat(emailInput.getAttribute("value"), CoreMatchers.is(email));
        passwordInput.sendKeys(password);
        MatcherAssert.assertThat(passwordInput.getAttribute("value"), CoreMatchers.is(password));
        signInSubmitButton.click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(2000L));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerUser));
        MatcherAssert.assertThat(headerUser.getText(), CoreMatchers.is(userName));
        headerUserButton.click();
        Thread.sleep(5000);
        headerUser.isSelected();
        signOutButton.click();
    }

    @ParameterizedTest
    @CsvSource({
            "samplestesgreencity@gmail",
            "samplestesgreencity.com"
    })
    public void signInNotValidEmail(String email) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys("uT346^^^erw");
        MatcherAssert.assertThat(errorEmail.getText(), CoreMatchers.is("Please check if the email is written correctly"));
        closeButton.click();

    }

    @ParameterizedTest
    @CsvSource({
            "testerforapp2023@gmail.com, 1234, Password must be at least 8 characters long without spaces",
            "testerforapp2023@gmail.com, 123456789123456789123456789123456789, Password must be less than 20 characters long without spaces."
    })
    public void signInNotValidPassword(String email, String password, String message) {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        passwordLabel.click();
        MatcherAssert.assertThat(errorPassword.getText(), CoreMatchers.is(message));
        closeButton.click();

    }

    @ParameterizedTest
    @CsvSource({"testerforapp2023@gmail.com, 12345678, Bad email or password"})
    public void signInIncorrectData(String email, String password, String message) throws InterruptedException {
        signInButton.click();
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInSubmitButton.click();
        Thread.sleep(5000);
        WebElement errorSubmit = driver.findElement(By.xpath("//*[contains(@id,\"mat-dialog-\")]/app-auth-modal//app-sign-in/form/div[@class=\"alert-general-error ng-star-inserted\"]"));

        MatcherAssert.assertThat(errorSubmit.getText(), CoreMatchers.is(message));
        closeButton.click();
    }

    @ParameterizedTest
    @CsvSource({
            "magicnimfa@gmail.com",
            "testerforapp2023@gmail.com",
            "cowafo7557@bustayes.com",
            "xapajoy635@frandin.com"
    })
    public void forgotPasswordTest(String email) throws InterruptedException {
        signInButton.click();
        forgotPasswordButton.click();
        MatcherAssert.assertThat(troubleText.getText(), CoreMatchers.is("Trouble singing in?"));
        MatcherAssert.assertThat(troubleDetailsText.getText(), CoreMatchers.is("Enter your email address and we'll send you a link to regain access to your account."));
        emailInput.sendKeys(email);
        submitLoginLinkButton.click();
        Thread.sleep(2000);
        MatcherAssert.assertThat(sendLinkMessage.getText(), CoreMatchers.is("Restore password link was sent to your email address."));

    }
    @ParameterizedTest
    @CsvSource({
            "magicnimfa@gmail.com",
            "testerforapp2023@gmail.com",
            "cowafo7557@bustayes.com",
            "xapajoy635@frandin.com"
    })
    public void forgotPasswordLinkAlreadySent(String email) throws InterruptedException {
        signInButton.click();
        forgotPasswordButton.click();
        MatcherAssert.assertThat(troubleText.getText(), CoreMatchers.is("Trouble singing in?"));
        MatcherAssert.assertThat(troubleDetailsText.getText(), CoreMatchers.is("Enter your email address and we'll send you a link to regain access to your account."));
        emailInput.sendKeys(email);
        submitLoginLinkButton.click();
        Thread.sleep(2000);
        MatcherAssert.assertThat(errorSubmitLink.getText(), CoreMatchers.is("Password restore link already sent, please check your email: " + email));
        MatcherAssert.assertThat(backToSignInText.getText(), CoreMatchers.is("Remember your password? Back to Sign in"));
        backToSignInButton.click();
        closeButton.click();

    }

    @Test
    public void redirectSignUp() {
        signInButton.click();
        MatcherAssert.assertThat(redirectSignUpText.getText(), CoreMatchers.is("Don't have an account yet? Sign up"));
        redirectSignUpButton.click();
        MatcherAssert.assertThat(helloText.getText(), CoreMatchers.is("Hello!"));
        MatcherAssert.assertThat(signUpDetailsText.getText(), CoreMatchers.is("Please enter your details to sign up."));
        closeButton.click();
    }
//    @Test
//    public void sighInGoogle() {
//        signInButton.click();
//        signInGoogleButton.click();
//        MatcherAssert.assertThat(signInGoogleText.getText(), CoreMatchers.is("Вход через аккаунт Google"));
//    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
