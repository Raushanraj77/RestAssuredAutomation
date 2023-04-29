import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import files.RequestPayload;
import files.ReusableMethods;

public class Basic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Validate Add Place API for Google Map
		
		//Given - All input - these 3 methods are static so need to write manually import address.
		//When - Send request to API (resourse and http method)
		//Then - Validate response 
		//log().all() - to get log for that method
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(RequestPayload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		//JsonPath js = new JsonPath(response); // parsing json
		JsonPath js = ReusableMethods.rawToJSON(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		//Add Place, Update place with new address, Validate new address updated
		
		//Update Place
		String newAddress = "Teakri, Gaya Bihar";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReusableMethods.rawToJSON(getPlaceResponse); //using reusable method
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress); //using testng
		
	}

}
