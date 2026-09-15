package RequestResponseSpecification;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecTest {
	
	
	RequestSpecification reqSpec , reqQueryparamSpec , reqSpecInvalidAuth;
	ResponseSpecification responseSpec;
	ResponseSpecification responseSpec_401;

	@BeforeTest
	public void setUp() {

	reqSpec= RestAssured.given().log().all()
		.baseUri("https://gorest.co.in/")
		.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.header("Content-Type", "application/json");

	reqQueryparamSpec= RestAssured.given().log().all()
			.baseUri("https://gorest.co.in/")
			.queryParam("name", "Giriraaj")
			.queryParam("status", "inactive")
			.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
			.header("Content-Type", "application/json");

	responseSpec= RestAssured.expect().log().all()
	    .statusCode(200)
	    .header("Content-Type","application/json; charset=utf-8")
	    .header("Server", "cloudflare")
	    .time(lessThan(5000L));
	
	reqSpecInvalidAuth= RestAssured.given().log().all()
			.baseUri("https://gorest.co.in/")
			.header("Authorization","Bearer Ponraj")
			.header("Content-Type", "application/json");

	responseSpec_401= RestAssured.expect().log().all()
		    .statusCode(401)
		    .header("Content-Type","application/json; charset=utf-8")
		    .header("Server", "cloudflare")
		    .time(lessThan(5000L))
		    .body("message",equalTo("Invalid token"));
	}
	
	@Test
	public void getUserTest() {
		reqSpec
		.get("/public/v2/users/")
		.then().log().all()
		.spec(responseSpec);
	}

	@Test
	public void getSingleUserTest() {
		reqSpec
		.get("/public/v2/users/8612641")
		.then().log().all()
		.spec(responseSpec);
	}
	
	@Test
	public void getQueryParamUserTest() {
		reqQueryparamSpec
		.get("/public/v2/users/")
		.then().log().all()
		.spec(responseSpec);
	}
	
	@Test
	public void invalidAutHTest() {
		reqSpecInvalidAuth
		.get("/public/v2/users/")
		.then().log().all()
		.spec(responseSpec_401);
	}

}
