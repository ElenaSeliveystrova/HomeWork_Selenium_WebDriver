package greenCity.test;

import greenCity.library.GuestFunctions;
import greenCity.locators.ForgotPasswordElements;
import greenCity.locators.SignInElements;
import greenCity.tools.LocalStorageJS;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public abstract class TestRunner {
    private static String BASE_URL = "https://www.greencity.social/";
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    protected static Wait<WebDriver> wait;
    protected static GuestFunctions guestFunctions;
    protected static SignInElements signInElements;
    protected static ForgotPasswordElements forgotPasswordElements;
    protected static LocalStorageJS localStorageJS;

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
        guestFunctions = new GuestFunctions(driver, webDriverWait, wait);
        signInElements = new SignInElements(driver);
        forgotPasswordElements = new ForgotPasswordElements(driver);
    }

    @AfterEach
    public void closePage() {
        driver.manage().deleteAllCookies();
        localStorageJS.removeItemFromLocalStorage("accessToken");
        localStorageJS.removeItemFromLocalStorage("refreshToken");

    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

}
