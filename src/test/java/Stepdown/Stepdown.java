package Stepdown;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdown {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expJ,expN;
	@Given("Users is on URL")
	public void users_is_on_url() {
		RestAssured.baseURI="https://regres.in/";
		   req=RestAssured.given();
		   System.out.println("Given step");
	}

	@When("Users enters the (.*) and (.*)$")
	public void users_enters_the_and(String name, String job) {
		res=req.get("api/user?page=2");
	    System.out.println("When step");
	}

	@Then("user are added to list")
	public void user_are_added_to_list() {
		path=res.jsonPath();
	    String id=path.getString("data[1].id");
	    Assert.assertEquals(id,"8","id mismatched");
	    System.out.println("Then step");
	}


}
