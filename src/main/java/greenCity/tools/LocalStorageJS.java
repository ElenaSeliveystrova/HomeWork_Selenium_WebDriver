package greenCity.tools;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
@Slf4j
public class LocalStorageJS {
    private JavascriptExecutor js;

    public LocalStorageJS(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public void clearLocalStorage() {
       js.executeScript(String.format("window.localStorage.clear();"));
    }
    public void removeItemFromLocalStorage(String item) {
        js.executeScript(String.format("window.localStorage.removeItem('%s');", item));
    }
    public void clickRedirectSignUpButton() {
        js.executeScript("document.querySelector('a.ubs-link').click()");
        log.info("Click on SignUp button");
    }
}
