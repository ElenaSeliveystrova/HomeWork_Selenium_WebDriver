package greenCity.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
    }
}
