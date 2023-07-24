package in.reqres.tests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CommonSpec.requestSpec;
import static in.reqres.specs.RegisterSpec.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
	String email = "eve.holt@reqres.in";
	String password = "cityslicka";

	@Test
	public void login() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);
		String token = step("Make request", () -> RestAssured
				.given(requestSpec)
				.body(user)
				.post("/login")
				.then()
				.spec(registerResponseSpec)
				.extract()
				.response().path("token"));
		step("Check response", () -> {
			assertEquals(token, "QpwL5tke4Pnpja7X4");
		});
	}

	@Test
	public void register() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);
		String token = step("Make request", () -> RestAssured
				.given(requestSpec)
				.body(user)
				.post("/register")
				.then()
				.spec(registerResponseSpec)
				.extract()
				.response().path("token"));
		step("Check response", () -> {
			assertEquals(token, "QpwL5tke4Pnpja7X4");
		});
	}

	@Test
	public void errorMissingPasswordRegister() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		String error = step("Make request", () -> RestAssured
				.given(requestSpec)
				.body(user)
				.post("/register")
				.then()
				.spec(registerResponseSpec400Error)
				.extract()
				.response().path("error"));
		step("Check response", () -> {
			assertEquals(error, "Missing password");
		});
	}

	@Test
	public void errorMissingPasswordLogin() {
		JSONObject user = new JSONObject();
		user.put("email", email);
		String error = step("Make request", () -> RestAssured
				.given(requestSpec)
				.body(user)
				.post("/login")
				.then()
				.spec(registerResponseSpec400Error)
				.extract()
				.response().path("error"));
		step("Check response", () -> {
			assertEquals(error, "Missing password");
		});
	}

	@Test
	public void findAnyFirstName() {
		step("Make request", () -> RestAssured
				.given(requestSpec)
				.get("/users?delay=3")
				.then()
				.spec(registerResponseSpecFindUsers)
				.body("data.first_name", hasItems("George", "Janet")));
	}
}
