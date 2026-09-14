package FakeAPIJayWayPath;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ThreeAttributeFakeAPITest {
	
	@Test
public void fakeAPITest() {
		
		RestAssured.baseURI="https://fakestoreapi.com";
		Response response= RestAssured.given()
		    .when()
		    .get("/products");
		
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);
		
		
		String jsonResponse = response.getBody().asString();
		ReadContext context = JsonPath.parse(jsonResponse);
		
		List<Map<String, Object>> threeAttribute=context.read("$[*]['id','title','category']");
		System.out.println(threeAttribute.size());
		System.out.println(threeAttribute);
		
		for(Map<String, Object> e :threeAttribute ) {
			int id=	(Integer)e.get("id");
			String title=	(String)e.get("title");
			String category=	(String)e.get("category");
			System.out.println("Id = " +id+ " " +"title = "+title+" "+"Categories = "+category);
			}
			
			
			System.out.println("--------------------------------------");
}
}
