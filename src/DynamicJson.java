

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.RequestPayload;
import files.ReusableMethods;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "bookDetails")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json").
		body(RequestPayload.addBook(isbn, aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = ReusableMethods.rawToJSON(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="bookDetails")
	public Object[][] getData()
	{
		//array = collection of elements
		//multi dimensional array = collection of arrays
		
		return new Object[][] {{"feddf", "5534"},{"erewc", "4553"},{"cxvdd", "7876"}};
	}

}
