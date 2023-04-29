package files;

public class RequestPayload {

	public static String addPlace() {
		return "{\n"
				+ "  \"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "  },\n"
				+ "  \"accuracy\": 50,\n"
				+ "  \"name\": \"Frontline house\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}";
		
	}
	
	public static String addBook(String isbn, String aisle) {
		
		String payload = "{\n"
		+ "\n"
		+ "\"name\":\"Learn Appium Automation with Javas\",\n"
		+ "\"isbn\":\""+isbn+"\",\n"
		+ "\"aisle\":\""+aisle+"\",\n"
		+ "\"author\":\"John foes\"\n"
		+ "}";
		return payload;
		
	}
}
