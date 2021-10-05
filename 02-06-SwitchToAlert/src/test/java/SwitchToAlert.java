import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;


public class SwitchToAlert{
    private WebDriver driver;

    @Before
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() {

        driver.get("https://formy-project.herokuapp.com/switch-window");
       
        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();  //1. open the alert.

        Alert alert = driver.switchTo().alert();   //2. switch WebDrive focus to the alert.

        alert.accept();    //3. press OK and then close the alert.

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