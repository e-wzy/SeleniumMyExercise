import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

//for xlsx
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

//for xls
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.Reporter;


import java.io.*;

public class FormPage {

    //data member
    private WebDriver  driver;
    private String first_name="";
    String last_name = "";
    private String job_title = "";
    private String picked_date = "";

    //locators
    String first_name_id = "first-name";
    String last_name_id = "last-name";
    String job_title_id = "job-title";
    String radio_button2_id = "radio-button-2";
    String checkbox2_id = "checkbox-2";
    String yrs_exp_opt1_css  = "option[value='1']";
    String date_picker_id = "datepicker";
    String submit_class_css = ".btn.btn-lg.btn-primary";

    //Constructor
      FormPage (String firstName, String lastName, String jobTitle, String pickedDate )
      {
          first_name  =  firstName;
          last_name   =  lastName;
          job_title   =  jobTitle;
          picked_date =  pickedDate;
      }

    //method
    public void submitForm(WebDriver Driver) throws IOException {
        driver = Driver;
        driver.findElement(By.id(first_name_id)).sendKeys(first_name);

        driver.findElement(By.id(last_name_id)).sendKeys(last_name);

        driver.findElement(By.id(job_title_id)).sendKeys(job_title);

        driver.findElement(By.id(radio_button2_id)).click();

        driver.findElement(By.id(checkbox2_id)).click();

        driver.findElement(By.cssSelector(yrs_exp_opt1_css)).click();

        driver.findElement(By.id(date_picker_id)).sendKeys(picked_date);
        driver.findElement(By.id(date_picker_id)).sendKeys(Keys.RETURN);
        Reporter.log(("All data entered."));
        //The code below is not necessary, just wait a while to observe running result.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector(submit_class_css)).click();

        Reporter.log("The form submitted.");
    }

}