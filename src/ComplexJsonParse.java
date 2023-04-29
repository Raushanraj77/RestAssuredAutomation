import files.ComplexJsonPayload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(ComplexJsonPayload.coursePrice());
		
		//Print no. of courses
		
		int numberOfCourses = js.getInt("courses.size()");
		System.out.println(numberOfCourses);
		
		//print purchase amount
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//print title of first course
		
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println(firstCourseTitle);
		
		//print all courses and respective prices
		
		for (int i=0; i<numberOfCourses; i++)
		{
			String allCoursesTitle = js.get("courses["+i+"].title");
			System.out.println(allCoursesTitle);
			int allCoursesPrice = js.getInt("courses["+i+"].price");
			System.out.println(allCoursesPrice);			
		}	
		
		//print no. of copies sold by RPA
		for(int j=0; j<numberOfCourses; j++)
		{
			String allCoursesTitle = js.get("courses["+j+"].title");
			if(allCoursesTitle.equalsIgnoreCase("RPA"))
			{
				int soldCopiesOfRPA = js.getInt("courses[2].copies");
				System.out.println(soldCopiesOfRPA);
				break;
			}
		}		
		
		//verify if sum of all course price matches with purchase amount
		//it is in SumValidation.Java
		
		
	}

}
