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

    @BeforeTest(groups = {"Group-1", "Group-2"})
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
        driver.get(url);
        FormPage formPage=new FormPage(driver);
        formPage.AssertFormPage();
        System.out.println("Form page loaded.");

    }
    @Test(groups = {"Group-1"})
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
    @Test(groups = {"Group-1", "Group-2"})
    public void testTwo() {
        System.out.println("I am in test 2");
        Reporter.log("\"I am in test 2.");
    }

    @Test(groups = {"Group-2"})
    public void testThree()
    {
        assertTrue(false);
        Reporter.log("Test to fail.");
    }

    @Test(priority = 1) //second
    public void testFour()
    {
        System.out.println("I am in test 4");
        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

         WebDriver driver1 = new ChromeDriver();
         driver1.get("https://www.javatpoint.com/testng-tutorial");
        //The code below is not necessary, just wait a while to observe running result.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver1.close();
    }

    @Test(priority = 0)  //first
    public void testFive()
    {
        System.out.println("I am in test 5");
        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        WebDriver driver2 = new ChromeDriver();
         driver2.get("https://github.com");
        //The code below is not necessary, just wait a while to observe running result.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver2.close();
    }

    @AfterTest(groups = {"Group-1", "Group-2"})
    public void teardown(){
        //driver.close();     //close current window
        driver.quit();    //close all windows
    }
}