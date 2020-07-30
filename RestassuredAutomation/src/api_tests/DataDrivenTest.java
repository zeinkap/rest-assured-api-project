package api_tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest {
	
	@Test(dataProvider="userDataProvider")
	public void addUser(String name, String username, String email, String phone, String website) {
		//Specify base URI
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		//Request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request pay-load sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);
		requestParams.put("username", username);
		requestParams.put("email", email);
		requestParams.put("phone", phone);
		requestParams.put("website", website);
		
		httpRequest.header("Content-Type", "application/json");
		
		//Attach above data to the request
		httpRequest.body(requestParams.toJSONString());
		
		//Response Object
		Response response = httpRequest.request(Method.POST, "/users");
		
		//Print response body in console
		String responseBody = response.getBody().asString();
		System.out.println("Response body is => " + responseBody);
		
		assertThat(responseBody, containsString(name));
		assertThat(responseBody, containsString(username));
		assertThat(responseBody, containsString(email));
		assertThat(responseBody, containsString(phone));
		assertThat(responseBody, containsString(website));
		
		//Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code => " + statusCode);
		assertThat(statusCode, equalTo(201));

	}
	
	@DataProvider(name="userDataProvider")
	String [][] getData() {
		String userData[][] = {
				{"Jack Ma", "jackma", "jack.ma@email.com", "1-347-778-3323", "jackma.com"},
				{"Ruffy Rouch", "ruffy1", "ruffy.rouch@email.com", "1-782-333-2232", "ruffy.com"},
				{"Donal Duck", "ddduck", "the.donald@email.com", "1-718-656-4069", "dduck.com"}
		};
		return userData;
	}

}
