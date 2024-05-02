package test_ng.testing.learining;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Automated_testing {
    WebDriver driver;
    String loanAmount;
    FileInputStream file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    ExtentReports extent;

    @BeforeTest
    public void beforeTest_new() {

        
    }
    
    @BeforeMethod
    public void beforeTest() throws IOException {
        driver = new ChromeDriver();
        file = new FileInputStream("D:\\Q1 & Q2\\testing\\testing\\src\\test\\java\\test_ng\\testing\\Book1.xlsx");
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
        loanAmount = sheet.getRow(0).getCell(0).getStringCellValue();
        extent = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(
            "D:\\Q1 & Q2\\testing\\testing\\src\\test\\java\\test_ng\\testing\\learining\\reporter.html");
        extent.attachReporter(reporter);
    
        reporter.config().setDocumentTitle("LAB REPORT");
        reporter.config().setTheme(Theme.DARK);
    }

    @Test
    public void loginTest() throws InterruptedException {
        ExtentTest test = extent.createTest("Testing calculator , update and test");
        driver.get("https://groww.in/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[3]/div/div[1]/div/div[1]/div[2]/div[3]/a[2]   ")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/a[15]/div")).click();
        Thread.sleep(3000);
        WebElement loaElement = driver.findElement(By.xpath("//*[@id=\"LOAN_AMOUNT\"]"));
        loaElement.clear();
        loaElement.sendKeys(loanAmount);
        Thread.sleep(1000);
        WebElement interest = driver.findElement(By.xpath("//*[@id=\"RATE_OF_INTEREST\"]"));
        interest.clear();
        interest.sendKeys("8");
        Thread.sleep(1000);
        WebElement loanTenure = driver.findElement(By.xpath("//*[@id=\"LOAN_TENURE\"]"));
        loanTenure.clear();
        loanTenure.sendKeys("25");
        Thread.sleep(1000);
        String expectedOutput = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText();
        if(expectedOutput.equals("Your Amortization Details (Yearly/Monthly)"))
        {
            test.log(Status.PASS, "Element present");
        }
        else
        {
            test.log(Status.FAIL, "Element not present");
        }

       
    }

   

    @AfterMethod
    public void after_method() {
        extent.flush();
        driver.quit();
    }

    
}
