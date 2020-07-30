package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification request;
	public static Response response;
	public static JsonPath jsonPath;
	
	public static final Logger logger = LogManager.getLogger("async_logger");
	
	
	@BeforeClass
	public void setup() {
		logger.info("Logger name => " + logger.getName() + " and level set at => " + logger.getLevel()); 
		
	}
	
}
