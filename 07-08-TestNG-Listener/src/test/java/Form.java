/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

public class Form {
    private WebDriver driver;
    private String url= "https://formy-project.herokuapp.com/form";
    private String workingDir;
    private String testDataPath;
    private String testRsltPath;

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

        workingDir = Path.of("").toAbsolutePath().toString();
        testDataPath = workingDir + "\\test-data\\";
        testRsltPath = workingDir + "\\test-result\\";

        String pathToChromeDriver = workingDir + "\\chromedriver.exe";
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
    //This test is to test Submit Form
    public void testOne() throws IOException
    {
        //Load test data
        LinkedList<String> test_case_id_list = new LinkedList<String>();
        LinkedList<FormPage> test_data_list = new LinkedList<FormPage>();
        File src=new File( testDataPath + "TestData.xls");

        readFormData_xls(src,test_data_list,test_case_id_list);
        //System.out.println(test_data_list);  // can not print out object elementss
       // System.out.println(test_case_id_list); // can print out all element

        //Conduct DDT testing
       for ( int i=0; i< test_case_id_list.size(); i++) {
            FormPage formObj = test_data_list.get(i);
            formObj.submitForm(driver);

            ConfirmationPage cfmPage = new ConfirmationPage(driver, testRsltPath, test_case_id_list.get(i));
            cfmPage.AssertResult();

            driver.get(url);
        }

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
        assertTrue(true);
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

    //Read .xls file to create
    public void readFormData_xls(File src, LinkedList<FormPage> formlist, LinkedList<String> test_id_list ) throws IOException {
        HSSFWorkbook workbook;
        HSSFSheet sheet;
        HSSFCell cell;

        //Import excel sheet.
        // Load the file.
        FileInputStream finput = new FileInputStream(src);

        // Load the workbook.
        workbook = new HSSFWorkbook(finput);

        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);

        //Test data
        String test_id="";
        String first_name="";
        String last_name = "";
        String job_title = "";
        String picked_date = "";

        for (int i=1; i<=sheet.getLastRowNum(); i++)
        {
            //Import data for test case id
            cell = sheet.getRow(i).getCell(0);
            cell.setCellType(CellType.STRING);

            test_id = cell.getStringCellValue();
            System.out.println(" test case id " + test_id);

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

            //Form link list
            FormPage  formObj = new FormPage(first_name, last_name, job_title, picked_date);
            formlist.add(formObj);
            test_id_list.add(test_id);

            /*// Write data in the excel.
            FileOutputStream foutput=new FileOutputStream(src);

            // Specify the message needs to be written.
            String message = "Data Imported Successfully.";

            // Create cell where data needs to be written.
            sheet.getRow(i).createCell(5).setCellValue(message);

            // finally write content
            workbook.write(foutput);

            // close the file
            foutput.close();*/
        }//for

    }//readExcel_xls
    public void readFormData_xlsx() throws IOException {
        //for xlsx
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFCell cell;

        //Import excel sheet.

        File src=new File( testDataPath + "TestData.xlsx");

        // Load the file.
        FileInputStream finput = new FileInputStream(src);

        // Load he workbook.
        workbook = new XSSFWorkbook(finput);

        // Load the sheet in which data is stored.
        sheet= workbook.getSheetAt(0);

        //Test data
        String test_id="";
        String first_name="";
        String last_name = "";
        String job_title = "";
        String picked_date = "";

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

    }//readExcel_xlsx
}