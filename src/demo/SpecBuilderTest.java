package demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import files.RequestPayload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilderTest {

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
		
		//Implement Reusable method
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res=given().spec(req)				
		//Replaced by "RequestSpecBuilder"
		//Response res=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(p); //spliting request seperately
		Response response = res.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		//Removed by line 53
		//.then().assertThat().statusCode(200).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);
	}

}
