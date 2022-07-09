package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;



import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;	
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefination  extends Utils {
	RequestSpecification res;
    Response response;
	static ResponseSpecification respp;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	 @Given("^Addplace payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	    public void addplace_payload_with_something_something_something(String name, String language, String address) throws Throwable {
			
		 respp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	 res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	 }
	
	 @When("^User calls \"([^\"]*)\" using \"([^\"]*)\" http request$")
	    public void user_calls_something_using_something_http_request(String resources, String method) throws Throwable {


		 APIResources resourcesAPI = APIResources.valueOf(resources);
		System.out.println(resourcesAPI.getResources());
		
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourcesAPI.getResources()).then().spec(respp).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourcesAPI.getResources()).then().spec(respp).extract().response();

	    }








	@Then("^the API call got success with status code 200$")
	public void the_api_call_got_success_with_status_code_200() throws Throwable {
		assertEquals(response.getStatusCode(),200);

	}

	@And("^\"([^\"]*)\" in reponse body is \"([^\"]*)\"$")
	public void something_in_reponse_body_is_something(String keyValue, String ExpectedValue) throws Throwable {
		
		assertEquals(getJsonPath(response, keyValue),ExpectedValue);

	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		 placeId = getJsonPath(response, "place_id");
		 res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		 user_calls_something_using_something_http_request(resource,"GET");
		 String actualName = getJsonPath(response,"name");
		 System.out.println(actualName);
		 System.out.println(expectedName);
		// assertEquals("expectedName","actualName");
	}
	


	@Given("DeletePlace PayLoad")
	public void delete_place_pay_load() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println(placeId);
		 res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(placeId));

	}







}
