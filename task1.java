package Fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class task1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
     //Launch of Firefox browser
      WebDriver driver = new FirefoxDriver();
      driver.get("https://www.fitpeo.com/");
      driver.manage().window().maximize();
      Thread.sleep(2000);
      
      //click on revenue calculator
      driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();
      Thread.sleep(2000);
      
      // Scroll action
      JavascriptExecutor js=(JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,500)");
      Thread.sleep(3000);
      
      //slider action 
      WebElement slider = driver.findElement(By.xpath("//span[@data-index='0']"));
      Thread.sleep(2000);
      Actions act= new Actions(driver);
      act.dragAndDropBy(slider,94,0).perform();
      WebElement enter = driver.findElement(By.xpath("//input[@type='number' and @max='2000']"));
     Thread.sleep(2000);
     act.dragAndDropBy(slider,-92,20).perform();
     Thread.sleep(2000);
     enter.clear();
     Thread.sleep(2000);
     
     // Value entered in the slider text field i.e no of patients
     WebElement no_of_patients = driver.findElement(By.xpath("//input[@type='number' and @max='2000']"));
       no_of_patients.sendKeys("560");
      String patients_value = no_of_patients.getAttribute("value");
      int total_patients = Integer.parseInt(patients_value);
      
      Thread.sleep(2000);
      
     // check boxes selection CPT codes 
      //driver.findElement(By.xpath("//span[text()='57']/../span/input")).click();
        WebElement CPT99091 = driver.findElement(By.xpath("//p[text()='CPT-99091']/../label[1]/span[2]"));
        CPT99091.click();
        double CPT99091_value = Double.parseDouble(CPT99091.getText().trim());
        
        WebElement CPT99453 = driver.findElement(By.xpath("//p[text()='CPT-99453']/../label[1]/span[2]"));
        CPT99453.click();
        double CPT99453_value =  Double.parseDouble(CPT99453.getText().trim());
        
        WebElement CPT99454 = driver.findElement(By.xpath("//p[text()='CPT-99454']/../label[1]/span[2]"));
        CPT99454.click();
        double CPT99454_value = Double.parseDouble(CPT99454.getText().trim());
        
        WebElement CPT99474 = driver.findElement(By.xpath("//p[text()='CPT-99474']/../label[1]/span[2]"));
        CPT99474.click();
        double CPT99474_value = Double.parseDouble(CPT99474.getText().trim());
        
      //Calculation of Total Recurring Reimbursement for all Patients Per Month:
        double Total_Recurring_Reimbursement_all_Patients_Per_Month = (CPT99091_value + CPT99454_value+ CPT99474_value)* total_patients; 
       System.out.println("Total_Recurring_Reimbursement_all_Patients_Per_Month =" +Total_Recurring_Reimbursement_all_Patients_Per_Month);
         
       // calculation of One Time Reimbursement for all Patients Annually   
       double One_Time_Reimbursement_for_all_Patients_Annually = CPT99453_value * total_patients;
       System.out.println("One_Time_Reimbursement_for_all_Patients_Annually ="+ One_Time_Reimbursement_for_all_Patients_Annually);
       
       // calculation of Total Gross Revenue Per Year
       double Total_Gross_Revenue_Per_Year = One_Time_Reimbursement_for_all_Patients_Annually + (Total_Recurring_Reimbursement_all_Patients_Per_Month * 12 );
       System.out.println("Actual_Total_Gross_Revenue_Per_Year ="+ Total_Gross_Revenue_Per_Year);
       
       // check the expected and actual Total_Gross_Revenue_Per_Year value
       int expected_Total_Gross_Revenue_Per_Year = 110700;
       System.out.println("expected_Total_Gross_Revenue_Per_Year ="+ expected_Total_Gross_Revenue_Per_Year);
       if (Total_Gross_Revenue_Per_Year == expected_Total_Gross_Revenue_Per_Year ) {
    	   System.out.println("Test cases passed");
       }
       else {
    	   System.out.println("Test case failed");
       }
   // driver.close();
      }

}
