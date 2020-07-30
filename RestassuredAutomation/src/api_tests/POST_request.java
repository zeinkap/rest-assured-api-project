package api_tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.RestUtils;

public class POST_request extends base.TestBase {
	
	String title = RestUtils.rTitle();
	String body = RestUtils.rBody();
	
	@BeforeClass
	public void addPost() {
		logger.info("***********Started TC010_Add_Post***************");
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		request = RestAssured.given();
		response = request.get("/users");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void sendPost() {
		//Request pay-load sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("title", title);
		requestParams.put("body", body);
		
		request.header("Content-Type", "application/json");
		
		//Attach above data to the request
		request.body(requestParams.toJSONString());
		
		//now we send request
		Response response = request.post("/posts");
		
		//Print response in console
		String responseBody = response.getBody().asString();
		logger.info("Response body => " + responseBody);
		
		//Status code validation
		int statusCode = response.getStatusCode();
		logger.info("Status code => " + statusCode);
		assertThat(statusCode, equalTo(201));
		
		//Header content-type validation
		String contentType = response.header("Content-type");
		logger.info("Content type is => " + contentType);
		assertThat(contentType, containsString("application/json"));
		//Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		//Header server validation
		String server = response.header("Server");
		logger.info("Server is => " + server);
		assertThat(server, equalToIgnoringCase("cloudflare"));
	}
	
}
