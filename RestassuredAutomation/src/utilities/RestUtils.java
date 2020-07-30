package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String rTitle() {
		String randomString = RandomStringUtils.randomAlphabetic(8);
		return randomString;
	}
	
	public static String rBody() {
		String randomString = RandomStringUtils.randomAlphabetic(20);
		return randomString;
	}
	
	public static String rName() {
		String randomString = RandomStringUtils.randomAlphabetic(2);
		return ("John" + randomString);
	}
	
	public static String rNum() {
		String randomString = RandomStringUtils.randomNumeric(2);
		return randomString;
	}
}
