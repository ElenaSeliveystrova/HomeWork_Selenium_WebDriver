package greenCity.test;

import greenCity.library.GuestFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class TestRunner {
    private static String BASE_URL = "https://www.greencity.social/";
    protected static WebDriver driver;
    protected static WebDriverWait webDriverWait;
    protected static Wait<WebDriver> wait;
    protected static GuestFunctions guestFunctions;
    protected static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.manage().window().setSize(new Dimension(1264, 798));
//        driver.manage().window().maximize();
    }

    @BeforeEach
    public void initPageElements() {
        PageFactory.initElements(driver, this);
        driver.manage().deleteAllCookies();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(5000L));
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(500));
        guestFunctions = new GuestFunctions(driver, webDriverWait, wait);
        js = (JavascriptExecutor) driver;
        // ToDo delete token
    }
    @AfterAll
    public static void tearDown() {
        driver.quit();
    }


}
