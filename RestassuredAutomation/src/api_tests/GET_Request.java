package api_tests;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.RestUtils;

public class GET_Request extends base.TestBase {
	
	String name = RestUtils.rName();
	String address = RestUtils.rNum();
	
	@BeforeClass
	public void getUsers() {
		logger.info("***********Started TC001_Get_All_Users***************");
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		request = RestAssured.given();
		response = request.get("/users");
	}
	
	@Test
	public void checkResponseBody() {
		logger.info("***********Checking response body***************");
		// to send query parameters along with request
//		Response response = request.queryParam("q", "Zaza")
//				.queryParam("appid", "32bsadfgsdfg")
//				.get("/users");
		
		//Response body validation
		String responseBody = response.getBody().asString(); // to convert from JSON to string format
		//System.out.println("Response body => " + responseBody);
		assertThat(responseBody, is(notNullValue()));
		logger.info("Retrieved response body!");
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("***********Checking response status code***************");
		//Status code validation
		int statusCode = response.getStatusCode();
		logger.info("Status code => " + statusCode);
		assertThat(statusCode, is(200));	// better way of asserting using hamcrest
		//Assert.assertEquals(statusCode, 200);
		logger.info("Asserted response status code");
	}
	
	//@Test
	public void checkResponseTime() {
		logger.info("***********Checking response time***************");
		//Response time verification
		int responseTime = (int) response.getTime();
		logger.info("Response time => " + responseTime);
		assertThat(responseTime, lessThan(3000));
		logger.info("Asserted response time is less than 3 seconds");
	}
	
	@Test
	public void checkSessionId() {
		logger.info("***********Checking session id***************");
		//Session id verification
		String sessionId = response.getSessionId();
		logger.info("Session id => " + sessionId);
		assertThat(sessionId, is(nullValue()));
		logger.info("Asserted session id is null");
	}
	
	//@Test
	public void getAllHeaders() {
		//Get all headers (which are key-value pairs)
		Headers allHeaders = response.getHeaders();
		
		for(Header header:allHeaders) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		logger.info("Got all header key-value pairs");
		
	}
	
	//@Test
	public void validateJSONResponse() {
		//Specify base URI to RESTful web service
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		/* Get the RequestSpecification of the request that you want to sent
		to the server. The server is specified by the BaseURI that we have
		specified in the above step. */
		request = RestAssured.given();	//Request object.
		
		// Make a request to the server by specifying the method Type and the method URL.
		// This will return a response from server. Store the response in a variable.
		response = request.get("/users");
		
		// Validate JSON data
		jsonPath = response.jsonPath(); // capturing all JSON data from response and storing in variable
		logger.info("Name is: " + jsonPath.getString("name[0]"));
		logger.info("Username is: " + jsonPath.getString("username[0]")); 
		logger.info("Email is: " + jsonPath.getString("email[0]")); 
		logger.info("Zipcode is: " + jsonPath.getString("address.zipcode[0]")); 
		logger.info("The zipcode: " + jsonPath.getString("company.name[0]")); 
		
		assertThat(jsonPath.getString("name[0]"), equalToIgnoringCase("Leanne Graham"));
		assertThat(jsonPath.getString("username[0]"), equalToIgnoringCase("Bret"));
		assertThat(jsonPath.getString("email[0]"), equalToIgnoringCase("Sincere@april.biz"));
		assertThat(jsonPath.getString("address.zipcode[0]"), equalToIgnoringCase("92998-3874"));
		assertThat(jsonPath.getString("company.name[0]"), equalToIgnoringCase("Romaguera-Crona"));
		
		//Print response body in console
		String responseBody = response.getBody().asString();
		logger.info("Response Body is => " + responseBody);
		
		//Validate title in JSON response body
		assertThat(responseBody, containsString("Clementine Bauch"));
	}
	
	
	//@Test
	public void checkingforAuthentication() {
		//Specify base URI
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		//Basic authentication
		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		auth.setUserName("admin");
		auth.setPassword("pass");
		
		RestAssured.authentication = auth; // specifying type of authentication using
				
		//Request object
		RequestSpecification httpRequest = RestAssured.given();	// authentication will get passed to request
		
		//Response Object
		Response response = httpRequest.get("/users");
		
		//Print response in console
		String responseBody = response.getBody().asString(); // to convert from JSON to string format
		logger.info("Response body => " + responseBody);
		
		//Status code validation
		int statusCode = response.getStatusCode();
		logger.info("Status code => " + statusCode);
		assertThat(statusCode, is(200));
		
	}
	
	@Test
	public void testParamsUsingDifferentWay() {
		logger.info("running api test using given, when, then");
		
		given()
//			.param("user", "")
//			.header("MyHeader", "some")
			
		.when()
			.get("https://jsonplaceholder.typicode.com/users")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("***********Finished TC001_Get_All_Users***************");
	}
	
}

	