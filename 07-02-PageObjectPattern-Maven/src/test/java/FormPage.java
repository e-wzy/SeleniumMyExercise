import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FormPage {

    //data member
    private WebDriver  driver;
    //locators
    String first_name_id = "first-name";
    String last_name_id = "last-name";
    String job_title_id = "job-title";
    String radio_button2_id = "radio-button-2";
    String checkbox2_id = "checkbox-2";
    String yrs_exp_opt1_css  = "option[value='1']";
    String date_picker_id = "datepicker";
    String submit_class_css = ".btn.btn-lg.btn-primary";

    //Test data
    String first_name="John";
    String last_name = "Doe";
    String job_title = "QA Engineer";
    String picked_date = "05/28/2019";

    //Constructor
      FormPage ( WebDriver Driver)
      {
           driver = Driver;
      }

    //method
    public void submitForm()
    {
        driver.findElement(By.id(first_name_id)).sendKeys(first_name);

        driver.findElement(By.id(last_name_id)).sendKeys(last_name);

        driver.findElement(By.id(job_title_id)).sendKeys(job_title);

        driver.findElement(By.id(radio_button2_id)).click();

        driver.findElement(By.id(checkbox2_id)).click();

        driver.findElement(By.cssSelector(yrs_exp_opt1_css)).click();

        driver.findElement(By.id(date_picker_id)).sendKeys(picked_date);
        driver.findElement(By.id(date_picker_id)).sendKeys(Keys.RETURN);

        driver.findElement(By.cssSelector(submit_class_css)).click();
    }
}