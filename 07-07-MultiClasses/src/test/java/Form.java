/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.*;

import java.io.IOException;

public class Form {
    private WebDriver driver;
    private String url= "https://formy-project.herokuapp.com/form";
    //----------------------------;
    // @BeforeTest and @AfterTest which have control over the particular folder not on the entire framework.
    // The tag @BeforeSuite has control over the whole XML file. The tag is the parent of all the test folders
    // @BeforeSuite: The @BeforeSuite annotated method is executed before the execution of all the test cases
    // defined in the folder of testng xml.
    //
    //The @BeforeTest annotated method will be executed before all the test methods
    //present in the classes which are kept inside the same test folder in testng xml
    //----------------------------

    //@BeforeTest
    @BeforeSuite
    public void setup(){
        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
        driver.get(url);

        //Ensure main page loaded.
        int timeout=5;
        String submit_class_css = ".btn.btn-lg.btn-primary";
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement submit = wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector(submit_class_css))));

        String submitText = submit.getText();
        assertEquals("Submit", submitText);
        System.out.println("First method - URL launched in setup().");
    }

    @Test
    public void testOne() throws IOException
    {
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

    @Test
    public void testTwo() throws IOException {
        System.out.println("I am in test 2 in Form class");
    }

    //------------------------------------------
    //The @AfterSuite annotated method is executed after the execution of
    // all the test methods in the Suite.
    // The @AfterTest annotated method run after the execution of all the test methods
    // present in the classes which are kept inside the same test folder in testng xml
    //----------------------------------------
    //@AfterTest
    @AfterSuite
    public void teardown(){
        //driver.close();     //close current window
        driver.quit();    //close all windows
        System.out.println("Last method - Tear down. ");
    }
}