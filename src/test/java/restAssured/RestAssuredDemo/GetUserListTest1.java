package restAssured.RestAssuredDemo;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUserListTest1 {
	RequestSpecification req;
	Response res;
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }
  @Test(priority=-1)
  
  public void getData() {
	  req=RestAssured.given();
	  res=req.get("api/users?page=1");
	  System.out.println(res.asString());
	  JsonPath data=res.jsonPath();
	  String email=data.getString("data[0].email");
	  System.out.println(email);
	  Assert.assertEquals(email, "mitchel.lawson@reqres.in");
  }
  
  public void addData() {
	  JSONObject obj=new JSONObject();
		obj.put("name", "Ajay");
		obj.put("job", "Software");
		req.body(obj.toJSONString());
		Response res=req.put("/api/users");
		System.out.println(res.asPrettyString());
  }
 
  @AfterTest
  public void deallocateMem()
  {
	  req= null;
	  res= null;
  }
  
}
