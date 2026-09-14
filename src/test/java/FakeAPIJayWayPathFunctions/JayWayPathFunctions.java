package FakeAPIJayWayPathFunctions;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JayWayPathFunctions {
	
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
	
	Double min_price=context.read("min($[*].price)");
	System.out.println("Minimum price is " +min_price);
	
	Double max_price=context.read("max($[*].price)");
	System.out.println("Maximum price is " +max_price);
	
	Double avg_price=context.read("avg($[*].price)");
	System.out.println("Average price is " +avg_price);
	
	Integer length=context.read("length()");
	System.out.println("length is " +length);
	
	Double sum_price=context.read("sum($[*].price)");
	System.out.println("Sum price is " +sum_price);
	
	Double first_price=context.read("first($[*]).price");
	System.out.println("first price is " +first_price);
	
	Double last_price=context.read("last($[*]).price");
	System.out.println("last price is " +last_price);
	
	/*find the prices or anything via Index*/
	
	Double index_1_price=context.read("index(1).price");
	System.out.println("first index price is " +index_1_price);
	
	
	Double index_3_price=context.read("index(3).price");
	System.out.println("first index price is " +index_3_price);
	
	
	Double last_index_price=context.read("index(-1).price");
	System.out.println("first index price is " +last_index_price);
	
	
	Double last_second_index_price=context.read("index(-2).price");
	System.out.println("first index price is " +last_second_index_price);
	
	System.out.println("--------------------------------------");
	
	//last index id and price index(-1).['id','price']
	
	Map<String,Object> lastindex_idtitle=context.read("index(-1).['id','title']");
	System.out.println("first index id and title  is " +lastindex_idtitle);
	
	
	System.out.println("--------------------------------------");
	
	//last index id and price index(-1).['id','price','category']
  
	
	Map<String,Object> lastindex_idtitlecategory=context.read("index(-1).['id','title','category']");
	System.out.println("first index id and title and catgegory is  " +lastindex_idtitlecategory);
	}

}
