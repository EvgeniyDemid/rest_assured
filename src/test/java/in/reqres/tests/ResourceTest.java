package in.reqres.tests;

import in.reqres.pojo.User.UserReq;
import in.reqres.pojo.User.UserRes;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;


public class ResourceTest {
	@BeforeAll
	static public void setup() {
		RestAssured.baseURI = "https://reqres.in";
	}

	@Test
	public void listResource() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/api/unknown")
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("page", equalTo(1))
				.body("per_page", equalTo(6))
				.body("total", equalTo(12))
				.body("total_pages", equalTo(2));

	}

	@Test
	public void singleResource() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/api/unknown/2")
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("data.id", equalTo(2))
				.body("data.name", equalTo("fuchsia rose"))
				.body("data.year", equalTo(2001))
				.body("data.color", equalTo("#C74375"))
				.body("data.pantone_value", equalTo("17-2031"));
	}
	@Test
	public void singleResourceNoFound() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/api/unknown/23")
				.then()
				.log()
				.all()
				.statusCode(404);
	}
}
