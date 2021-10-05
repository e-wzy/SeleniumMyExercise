import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;


public class SwitchToWindow{
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

        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();   // open a new window, at this point there are two tabs ( windows) open

        String originalHandle = driver.getWindowHandle();  // get handle of original window

        //Iterate through two windows, test will focus on switching to the new tab.
        //origin -> new window
        for(String handle1: driver.getWindowHandles()) {
            driver.switchTo().window(handle1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

       driver.switchTo().window(originalHandle);   // back to origin

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