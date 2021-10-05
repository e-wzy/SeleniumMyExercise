import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class KeyboardMouseInputTest {

     private WebDriver driver;

     @Before
     public void setup(){

          String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

          System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

          driver = new ChromeDriver();
     }
     @Test
     public void testOne() {
          driver.get("https://formy-project.herokuapp.com/keypress");

          WebElement name = driver.findElement(By.id("name"));
          name.click();
          name.sendKeys("Meaghan Lewis");

          WebElement button = driver.findElement(By.id("button"));
          button.click();
          try{
               Thread.sleep(2000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
}
          @After
     public void teardown(){
          driver.close();
     }

}
