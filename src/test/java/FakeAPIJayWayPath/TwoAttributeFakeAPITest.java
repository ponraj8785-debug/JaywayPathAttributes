package FakeAPIJayWayPath;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TwoAttributeFakeAPITest {
	
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
		
		List<Map<String, Object>> twoAttribute=context.read("$[*]['id','title']");
		System.out.println(twoAttribute.size());
		System.out.println(twoAttribute);
		
		for(Map<String, Object> e :twoAttribute ) {
		int id=	(Integer)e.get("id");
		String title=	(String)e.get("title");
		System.out.println("Id's " + id + " " +"title is "+title);
		}
		
		
		System.out.println("--------------------------------------");
}

}
