package RequestResponseSpecification;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestSpecTest {

	@Test

	public void requestSpecTest() {

		RequestSpecification reqTest = RestAssured.given().log().all().baseUri("https://jsonplaceholder.typicode.com/")
				.header("Content-Type", "application/json");
		
		//1./posts
		reqTest.get("/posts")
		       .then().log().all()
		       .statusCode(200);

		//2./comments
		
		reqTest.get("/comments")
		 .then().log().all()
	       .statusCode(200);
		
		
		//3.create a post call 
		
		reqTest.body("  {\r\n"
				+ "    \"userId\": 1001,\r\n"
				+ "    \"id\": 102,\r\n"
				+ "    \"title\": \"API Testing\",\r\n"
				+ "    \"body\": \"Love Automation API Tesing\"\r\n"
				+ "  }")
		.when()
		.post("/posts")
		.then().log().all()
	       .statusCode(201);
		
	}
	
	@Test

	public void goRestAPITest() {
		
		RequestSpecification goRestReq=	RestAssured.given().log().all()
				.baseUri("https://gorest.co.in/")
				.header("Content-Type", "application/json")
		        .header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb");
		
		//1.All the users
		goRestReq.get("/public/v2/users/")
		.then().log().all()
		.statusCode(200);
		
		//2.Specific users
		goRestReq.get("/public/v2/users/8612641")
		.then().log().all()
		.statusCode(200);
	}
	
	
	@Test

	public void goRestAPIQueryparamTest() {
		
		RequestSpecification goRestReq=	RestAssured.given().log().all()
				.baseUri("https://gorest.co.in/")
				.queryParam("name", "Giriraaj")
				.queryParam("status", "inactive")
				.header("Content-Type", "application/json")
		        .header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb");
		
		//1.get the users from query param
		goRestReq.get("/public/v2/users/")
		.then().log().all()
		.statusCode(200);
		
	
	}
}

