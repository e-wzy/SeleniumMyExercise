/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;
;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class Form {
    private WebDriver driver;
    private String url = "https://formy-project.herokuapp.com/form";

    @BeforeTest
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
        driver.get(url);
        FormPage formPage=new FormPage(driver);
        formPage.AssertFormPage();
        System.out.println("Form page loaded.");

    }
    @Test
    public void testOne() {
        FormPage formPage=new FormPage(driver);
        formPage.submitForm();
        Reporter.log("Form submitted.");

        ConfirmationPage cfmPage=new ConfirmationPage(driver);
        cfmPage.AssertResult();
        Reporter.log("Result asserted.");

        //The code below is not necessary, just wait a while to observe running result.
        try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    @Test
    public void testTwo() {
        System.out.println("I am in test 2");
        Reporter.log("\"I am in test 2.");
    }

    @Test
    public void testThree()
    {
        assertTrue(false);
        Reporter.log("Test to fail.");
    }
    @AfterTest
    public void teardown(){
        //driver.close();     //close current window
        driver.quit();    //close all windows
    }
}