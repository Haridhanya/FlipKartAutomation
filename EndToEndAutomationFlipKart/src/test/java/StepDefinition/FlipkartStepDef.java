package StepDefinition;




import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class FlipkartStepDef {
	List<WebElement>prd;
	
	WebDriver driver;
	@Before
	public void driverSetup()
	{
		WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
	}
	
	
	@Given("User enter the Url")
	public void user_enter_the_url() {
		driver.get("https://www.flipkart.com/");
	   
	}

@When("User Enter THe Product and search")
public void user_enter_t_he_product_and_search() {
   WebElement e=driver.findElement(By.name("q"));
   e.sendKeys("iphone");
   e.sendKeys(Keys.ENTER);   
}

@Then("Get the list of products")
public void get_the_list_of_products() throws IOException {
   prd=driver.findElements(By.xpath("//div[@class='col col-7-12']/div[@class='KzDlHZ']"));
	
	for(WebElement e:prd)
	{
		//System.out.println(e.getText());
		
	}
}


@Then("Output the result into Word File")
public void output_the_result_into_Word_file() throws IOException {


FileWriter file= new FileWriter("src/test/resources/productlist.txt");
BufferedWriter data1=new BufferedWriter(file);
for(WebElement e:prd)
{
	String list=e.getText();
	System.out.println(list);
data1.write(list);
data1.newLine();

}

data1.close();
}


@Then("Output the result into Excel File")
public void output_the_result_into_excel_file() throws IOException {
    //create Workbook
	XSSFWorkbook wb=new XSSFWorkbook();
	XSSFSheet sh=wb.createSheet("product list");
	//Create rows
	int i=0;
	for(WebElement e:prd)
	{
	String list=e.getText();
	XSSFRow rw=sh.createRow(i);
	rw.createCell(0).setCellValue(list);
	i++;
	
	}
	FileOutputStream file=new FileOutputStream("src/test/resources/productlist.xlsx");
	wb.write(file);
	
	
	

	
	
	
	
	
	
	
}


@After
public void teardown()
{
	driver.close();
}

}
