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
    private String test_data_path;
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
    //String first_name="John";
    //String last_name = "Doe";
    //String job_title = "QA Engineer";
    //String picked_date = "05/28/2019";
    String first_name="";
    String last_name = "";
    String job_title = "";
    String picked_date = "";


    //Constructor
      FormPage ( WebDriver Driver, String testDataPath)
      {
          driver = Driver;
          test_data_path = testDataPath;
      }

    //method
    public void submitForm() throws IOException {
        readExcel(); // load test data
        Reporter.log("Test data loaded.");

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

    public void readExcel() throws IOException {
        //for xlsx
        /*
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFCell cell;
        */

        HSSFWorkbook workbook;
        HSSFSheet sheet;
        HSSFCell cell;

        //Import excel sheet.
        //File src=new File("C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\07-05-DDT-Excel\\TestData.xlsx");
        File src=new File( test_data_path + "TestData.xls");

        // Load the file.
        FileInputStream finput = new FileInputStream(src);

        // Load he workbook.
        workbook = new HSSFWorkbook(finput);

        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);

        for (int i=1; i<=sheet.getLastRowNum(); i++)
        {
          // Import data for first name
            cell = sheet.getRow(i).getCell(1);
            cell.setCellType(CellType.STRING);

            first_name = cell.getStringCellValue();
            System.out.println(" first name: " + first_name);

            // Import data for last name
            cell = sheet.getRow(i).getCell(2);
            cell.setCellType(CellType.STRING);

            last_name = cell.getStringCellValue();
            System.out.println(" last name: " + last_name );

            // Import data for job title
            cell = sheet.getRow(i).getCell(3);
            cell.setCellType(CellType.STRING);

            job_title = cell.getStringCellValue();
            System.out.println(" job title: " + job_title );

            // Import data for picked_date
            cell = sheet.getRow(i).getCell(4);
            cell.setCellType(CellType.STRING);

            picked_date = cell.getStringCellValue();
            System.out.println(" picked date: " + picked_date );

            // Write data in the excel.
            FileOutputStream foutput=new FileOutputStream(src);

            // Specify the message needs to be written.
            String message = "Data Imported Successfully.";

            // Create cell where data needs to be written.
            sheet.getRow(i).createCell(5).setCellValue(message);

            // finally write content
            workbook.write(foutput);

            // close the file
            foutput.close();
        }//for

    }//readExcel

}