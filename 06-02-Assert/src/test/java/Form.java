import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

public class Form {
    private WebDriver driver;

    @Before
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() {

        driver.get("https://formy-project.herokuapp.com/form");

        driver.findElement(By.id("first-name")).sendKeys("John");

        driver.findElement(By.id("last-name")).sendKeys("Doe");

        driver.findElement(By.id("job-title")).sendKeys("QA Engineer");

        driver.findElement(By.id("radio-button-2")).click();

        driver.findElement(By.id("checkbox-2")).click();
        //The code below is not necessary, just wait a while to observe running result
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Open drop down menu
        driver.findElement(By.id("select-menu")).click();
        //Select an option
        driver.findElement(By.cssSelector("option[value='1']")).click();
        //The code below is not necessary, just wait a while to observe running result
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("datepicker")).sendKeys("05/28/2019");
        driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);

        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();

        //Assert result
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //The both ways below can work well.
        //WebElement alert = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("alert"))));
        WebElement alert = wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success"))));

        String alertText = alert.getText();

        assertEquals("The form was successfully submitted!", alertText);

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