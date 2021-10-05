import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollToElement {
    private WebDriver driver;

    @Before
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() {
        driver.get("https://formy-project.herokuapp.com/scroll");

        WebElement name = driver.findElement(By.id("name"));

        //The "moveToElement" could be necessary before sending value to it.
        Actions actions = new Actions(driver);
        actions.moveToElement(name);

        name.sendKeys("Meaghan Lewis");

        WebElement date = driver.findElement(By.id("date"));
        date.sendKeys("01/01/2020");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @After
    public void teardown(){
        //driver.close();
        driver.quit();
    }
}