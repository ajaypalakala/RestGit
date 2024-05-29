package TestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelReaderTest2 {
  RequestSpecification req;
	Response res;
	JsonPath data;
	File file;
	FileInputStream fis;
	XSSFWorkbook w;
	XSSFSheet s;
	@DataProvider(name="createUser")
	public Object[][] getData() throws IOException
	{
		file=new File("C:\\Users\\APALAKAL\\OneDrive - Capgemini\\Desktop\\Maven Project\\RestAssuredDemo\\src\\test\\resource\\New Microsoft Excel Worksheet.xlsx");
		  fis= new FileInputStream(file);
		  w= new XSSFWorkbook(fis);
		  s= w.getSheetAt(0);
		  int row= s.getPhysicalNumberOfRows();
		  int col= s.getRow(0).getPhysicalNumberOfCells();
		  Object[][] data=new Object[row][col];
		  for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				data[i][j]=s.getRow(i).getCell(j).toString();
			}
		}
		return data;
		
	}
	 @BeforeTest
	  public void init() {
		  RestAssured.baseURI="https://reqres.in/";
	  }
@Test(dataProvider = "createUser")

public void f(String name, String job) {
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("job", job);
		req.header("Content-Type", "application/json");
		res= req.body(obj.toJSONString()).post("api/users"); 
		System.out.println(obj);
		data= res.jsonPath();
		String jobdata= data.getString("job");
		System.out.println(jobdata);
		Assert.assertEquals(jobdata, job);
}
@AfterTest
public void deallocateMem() {
	  req=null;
	  res=null;
}

}
