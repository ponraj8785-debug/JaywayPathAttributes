package RequestResponseSpecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecTest {

	@Test

	public void goRestAPIResponseTest() {
		
	ResponseSpecification responseSpec=	RestAssured.expect()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
		
		
	RestAssured.given().log().all()
		.baseUri("https://gorest.co.in/")
		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.when()
		.get("/public/v2/users/")
		.then()
		.spec(responseSpec);
	
	RestAssured.given().log().all()
	    .baseUri("https://gorest.co.in/")
        .header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
        .when()
        .get("/public/v2/users/8612641")
        .then()
        .spec(responseSpec);

		
	}
}
