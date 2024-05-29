package TestNG;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class DataProTest1 {
	RequestSpecification req;
	Response res;
	JsonPath data;
	
	@DataProvider(name="createUser")
	public Object[][] getData()
	{
		Object[][] data=new Object[3][2];
		
		data[0][0] ="ajay";
		data[0][1] ="Software";
		
		data[1][0] ="ramu";
		data[1][1] ="Software";
		
		data[2][0] ="mani";
		data[2][1] ="tester";
		
		
		return data;
		
	}
	 @BeforeTest
	  public void init() {
		  RestAssured.baseURI="https://reqres.in/";
	  }
  @Test(dataProvider = "createUser")
  
  public void f(Integer name, String job) {
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
