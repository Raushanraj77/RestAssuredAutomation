package demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import files.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("Gaya Bihar");
		p.setLanguage("Hindi");
		p.setPhone_number("44234343433");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Monu");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Park");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.343432);
		l.setLng(38.343423);
		p.setLocation(l);
		
		Response res=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);
	}

}
