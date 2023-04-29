import org.testng.Assert;
import org.testng.annotations.Test;

import files.ComplexJsonPayload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourse()
	{
		int sum =0;
		JsonPath js = new JsonPath(ComplexJsonPayload.coursePrice());
		int numberOfCourses = js.getInt("courses.size()");
		
		for(int i=0; i<numberOfCourses; i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copy=js.getInt("courses["+i+"].copies");
			int amount = price * copy;
			sum = sum + amount;							
		}
		System.out.println(sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(purchaseAmount, sum);
	}
}
