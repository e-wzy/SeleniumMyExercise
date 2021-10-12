import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ConfirmationPage {
    //data member
    private WebDriver driver;
    private int timeout=10;
    //locators
     String alert_classname = "alert";

    //constructor
     ConfirmationPage(WebDriver Driver)
     {
         driver =Driver;
     }

    //method
    public void AssertResult() {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement alert = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className(alert_classname))));

        String alertText = alert.getText();
        assertEquals("The form was successfully submitted!", alertText);
    }
}
