import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Reporter;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.*;

public class ConfirmationPage {
    //data member
    private WebDriver driver;
    private int timeout=10;
    private String test_result_path;
    private String test_case_id;

    //locators
     String alert_classname = "alert";

    //constructor
     ConfirmationPage(WebDriver Driver, String testResultPath, String testCaseId)
     {
         driver =Driver;
         test_result_path = testResultPath;
         test_case_id = testCaseId;
     }

    //method
    public void AssertResult() {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement alert = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className(alert_classname))));

        String alertText = alert.getText();

        //Junit assert
        assertEquals("The form was successfully submitted!", alertText);

        //TestNG assert
        assertTrue(alert.isDisplayed(), "Confirmation page is not shown up!");
        System.out.println("Confirmation page displayed.");
        Reporter.log("Confirmation page shown up.");

        //Take screenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Copy file to test result folder
        File DestFile=new File(test_result_path+test_case_id+".jpg");

        try
        {
            FileUtils.copyFile(SrcFile, DestFile);
        }
        catch ( Exception e)
        {
            System.out.print("Error:"+ e.toString());
        }
        System.out.println("Confirmation page screenshot created.");

        Reporter.log("Confirmation page screenshot created.");
    }
}
