package Steps;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddUserTest {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expN, expJ;
	@Given("User is on  regres URL")
	public void user_is_on_regres_url() {
		RestAssured.baseURI="https://regres.in/";
		req = RestAssured.given();
		System.out.println("Given step");
	    
	}

	@When("^User enters the (.*) and (.*)$")
	public void user_enters_the_and(String name, String job) {
	    obj=new JSONObject();
	    obj.put("name", name);
	    obj.put("job", job);
	    expJ=job;
	    expN=name;
	    req.header("Content-Type", "application/json");
	    
	    System.out.println(obj);
	    System.out.println("When step");
	    }

	@And("users hit the users API")
	public void users_hit_the_users_api() {
		res=req.body(obj.toJSONString()).post("api/users");
		System.out.println("And step");
	    
	}
	@Then("users are added to list")
	public void users_are_added_to_list() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String job1= path.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1, expJ);    
	}
	

	//--------------------------------------scenario 2------
		/*
		 * 
		 * Created By: ajay
		 * Reviwed by: ramu
		 * updating the user
		 */
	@When("User enters name & job")
	public void user_enters_name_job(DataTable data) {
	    List<List<String>> udata=data.asLists(String.class);
	    String name=udata.get(0).get(0);
	    String job=udata.get(0).get(1);
	    /*Single value
	     * List<String> cell=data.asList(String.class);
	     * String name=cell.get(0);
	     */
	    obj=new JSONObject();
		obj.put("name",name);
		obj.put("job", job);
		expJ=job;
		expN=name;
		req.header("Content-Type","application/json");
		System.out.println(obj);
		System.out.println("When step");
	}
	 
	@And("user hits the users API")
	public void user_hits_the_users_api() {
		res=req.body(obj.toJSONString()).put("api/users/2");
		  System.out.println("And step");
	}
	 
	@Then("user data is updated")
	public void user_data_is_updated() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String jobdata=path.getString("job");
		System.out.println(jobdata);
		Assert.assertEquals(jobdata, expJ);
	}
	}