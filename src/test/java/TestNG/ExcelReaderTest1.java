package TestNG;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class ExcelReaderTest1 {
	RequestSpecification req;
	Response res;
	JsonPath data;
	private File file;
	private FileInputStream fis;
	XSSFWorkbook w;
	XSSFSheet s;
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }
  @Test(priority=-1)
  public void getData() {
	  req=RestAssured.given();
	  res=req.get("api/users?page=2");
	  System.out.println(res.asString());
	  JsonPath data=res.jsonPath();
	  String email=data.getString("data[1].email");
	  System.out.println(email);
	  Assert.assertEquals(email, "lindsay.ferguson@reqres.in");
	  String first_name=data.getString("data[0].first_name");
	  System.out.println(first_name);
	  Assert.assertEquals(first_name, "Michael");
	  String id=data.getString("data[0].id");
	  System.out.println(id);
	  Assert.assertEquals(id, "7");
	  String page=data.getString("page");
	  System.out.println(page);
	  Assert.assertEquals(page, "2");
  }
  @Test()
  @Parameters({"name","job"})
  public void addData() throws IOException {
	  file=new File("C:\\Users\\APALAKAL\\OneDrive - Capgemini\\Desktop\\Maven Project\\RestAssuredDemo\\src\\test\\resource\\New Microsoft Excel Worksheet.xlsx");
	  fis= new FileInputStream(file);
	  w=new XSSFWorkbook(fis);
	  s=w.getSheet("Userdata");
	  
	  int row=s.getPhysicalNumberOfRows();
	  System.out.println("Row: "+row);
	  int col=s.getRow(0).getPhysicalNumberOfCells();
	  System.out.println("Cols: "+col);
	  String name=s.getRow(2).getCell(0).toString();
	  String job=s.getRow(2).getCell(1).getStringCellValue();
	  //Properties p=new Properties();
	  //p.load(fis);
	 // String name=p.getProperty("name");
	  //String job=p.getProperty("job");
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("job", job);
		req.header("Content-Type", "application/json");
		res= req.body(obj.toJSONString()).post("api/users"); 
		System.out.println(obj);
		data= res.jsonPath();
		String job1= data.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1, job);
  }
  @AfterTest
  public void deallocateMem()
  {
	  req= null;
	  res= null;
  }
}


