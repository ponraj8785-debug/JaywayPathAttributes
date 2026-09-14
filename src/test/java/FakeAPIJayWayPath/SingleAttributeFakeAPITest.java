package FakeAPIJayWayPath;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.ReadContext;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SingleAttributeFakeAPITest {
	
	
	
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
	
	List<Number>prices=context.read("$[?(@.price>50)].price");
	System.out.println("Prices > 50's are " + prices.size());
	System.out.println(prices);
	
	
	System.out.println("--------------------------------------");
	
	List<Integer> ids=context.read("$[?(@.price>50)].id");
	System.out.println("Prices > 50s ids are " + ids.size());
	System.out.println(ids);
	
	
	System.out.println("--------------------------------------");
	
	
	List<Double> rate=context.read("$[?(@.price>50)].rating.rate");
	System.out.println("Prices > 50s Rates are " + rate.size());
	System.out.println(rate);
	

	System.out.println("--------------------------------------");
	
	List<Double> category=context.read("$[?(@.price > 50)].category");
	System.out.println("Prices > 50s categories are " + category.size());
	System.out.println(category);
	
	System.out.println("--------------------------------------");
	

	List<Double> jeweleryprice=context.read("$[?(@.category === 'jewelery' && @.price > 100)]");
	System.out.println("Jewelery Prices > 100 categories are " + jeweleryprice.size());
	System.out.println(jeweleryprice);
	
	System.out.println("--------------------------------------");
	
	List<Double> ratingCount=context.read("$[?(@.rating.count < 120)]");
	System.out.println("Rating Counts > 120" + ratingCount.size());
	System.out.println(ratingCount);
	
	
	}
	

}
