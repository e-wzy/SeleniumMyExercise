import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class ImplicitWait {
    private WebDriver driver;

    @Before
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() {

        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("1555 Park Blvd, Palo Alto, CA");

        // If the element is already there, then there is no wait time.
        // if the element is not there, the implicit wait is triggered and that element is polled up to the time specified.
        // The implicit wait not equal to Thread.sleep(x) which pauses execution.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
        autocompleteResult.click();

        //The code below is not necessary, just wait a while to observe running result.
        try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    @After
    public void teardown(){
        //driver.close();     //close current window
        driver.quit();    //close all windows
    }
}