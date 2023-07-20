package in.reqres.tests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
	String email = "eve.holt@reqres.in";
	String password = "cityslicka";

	@BeforeAll
	static public void setup() {
		RestAssured.baseURI = "https://reqres.in";
	}

	@Test
	public void login() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);
		String token = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(user)
				.post("/api/login")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.response().path("token");
		assertEquals(token, "QpwL5tke4Pnpja7X4");
	}

	@Test
	public void register() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);
		String token = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(user)
				.post("/api/register")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.response().path("token");
		assertEquals(token, "QpwL5tke4Pnpja7X4");
	}

	@Test
	public void errorMissingPasswordRegister() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		String error = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(user)
				.post("/api/register")
				.then()
				.log()
				.all()
				.statusCode(400)
				.extract()
				.response().path("error");
		assertEquals(error, "Missing password");
	}

	@Test
	public void errorMissingPasswordLogin() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		String error = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(user)
				.post("/api/login")
				.then()
				.log()
				.all()
				.statusCode(400)
				.extract()
				.response().path("error");
		assertEquals(error, "Missing password");
	}

	@Test
	public void findAnyFirstName() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/api/users?delay=3")
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("data.first_name", hasItems("George", "Janet"));
	}
}
