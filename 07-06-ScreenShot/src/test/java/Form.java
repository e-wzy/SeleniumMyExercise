/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Form {
    private WebDriver driver;

    @BeforeTest
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() throws IOException {

        driver.get("https://formy-project.herokuapp.com/form");

        FormPage formPage=new FormPage(driver);
        formPage.submitForm();

        ConfirmationPage cfmPage=new ConfirmationPage(driver);
        cfmPage.AssertResult();

        //The code below is not necessary, just wait a while to observe running result.
        try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
   /*
    @Test
    public void testTwo() throws IOException {
        System.out.println("I am in test 2 to read test data");
        FormPage formPage=new FormPage(driver);
        formPage.readExcel();
    }
*/
    @AfterTest
    public void teardown(){
        //driver.close();     //close current window
        driver.quit();    //close all windows
    }
}