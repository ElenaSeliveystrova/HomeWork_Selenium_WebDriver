package greenCity.test;

import greenCity.library.GuestFunctions;
import greenCity.locators.ForgotPasswordElements;
import greenCity.locators.SignInElements;
import greenCity.tools.LocalStorageJS;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@ExtendWith(RunnerExtension.class)
public abstract class TestRunner {
    private static String BASE_URL = "https://www.greencity.social/";
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    protected static Wait<WebDriver> wait;
    protected static GuestFunctions guestFunctions;
    protected static SignInElements signInElements;
    protected static ForgotPasswordElements forgotPasswordElements;
    protected static LocalStorageJS localStorageJS;
    protected static Boolean isTestSuccessful = false;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.manage().window().setSize(new Dimension(1264, 798));
//        driver.manage().window().maximize();
        localStorageJS = new LocalStorageJS(driver);
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);

        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500));
        signInElements = new SignInElements(driver);
        forgotPasswordElements = new ForgotPasswordElements(driver);
        guestFunctions = new GuestFunctions(driver, webDriverWait, wait);
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            log.error(testInfo.getDisplayName() + "fail");
            System.out.println(testInfo.getTestMethod() + " with test data " + testInfo.getDisplayName() + " failed");

        } else {
            log.info("Test" + testInfo.getTestMethod() + " finished successful " + isTestSuccessful);
            System.out.println("Test" + testInfo.getTestMethod() + " finished successful " + isTestSuccessful);
        }
        closeLoginForm();
        driver.manage().deleteAllCookies();
        localStorageJS.removeItemFromLocalStorage("accessToken");
        localStorageJS.removeItemFromLocalStorage("refreshToken");

    }

    public void closeLoginForm() {
        List<WebElement> closeButtonLoginForm = signInElements.listCloseButton;
        if(!closeButtonLoginForm.isEmpty()) {
            closeButtonLoginForm.get(0).click();
        }
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
