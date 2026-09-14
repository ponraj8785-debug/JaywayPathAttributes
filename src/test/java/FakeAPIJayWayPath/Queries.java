package FakeAPIJayWayPath;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Queries {

	@Test
	public void fakeAPITest() {

		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = RestAssured.given().when().get("/products");

		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);

		String jsonResponse = response.getBody().asString();
		ReadContext context = JsonPath.parse(jsonResponse);

		List<Map<String, Object>> query = context
				.read("$[?(@.price>110)].[?(@.category === 'jewelery' && @.price > 100)]");
		System.out.println(query.size());
		System.out.println(query);

		for (Map<String, Object> e : query) {
			int id = (Integer) e.get("id");
			String title = (String) e.get("title");
			int price = (Integer) e.get("price");
			String description = (String) e.get("description");
			String category = (String) e.get("category");
			System.out.println("Id's " + id + " " + "title is " + title + "Price is " + price + "Description is "
					+ description + "category is " + category);

		}

	}

}
