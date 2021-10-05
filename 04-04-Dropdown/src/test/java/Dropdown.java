import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class Dropdown{
    private WebDriver driver;

    @Before
    public void setup(){

        String pathToChromeDriver = "C:\\00-MyDisk\\01-Selenium\\00-MyExercise\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }
    @Test
    public void testOne() {
       
        driver.get("https://formy-project.herokuapp.com/dropdown");
        
         // Find dropdown menu element
        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));

        // Open dropdown menu
         dropDownMenu.click();
        //Wait a while to observe running result
        try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

       //Select an option.
        WebElement autocompleteItem = driver.findElement(By.id("autocomplete"));
        autocompleteItem.click();

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