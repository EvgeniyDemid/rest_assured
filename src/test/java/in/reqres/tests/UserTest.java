package in.reqres.tests;

import in.reqres.pojo.User.UserReq;
import in.reqres.pojo.User.UserRes;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CommonSpec.requestSpec;
import static in.reqres.specs.UserSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {
	String userName = "morpheus";
	String newJob = "zion resident";
	String job = "leader";

	@Test
	public void createUser() {
		UserReq newUser = new UserReq(userName, job);
		UserRes user = step("Make request", () -> RestAssured
				.given(requestSpec)
				.body(newUser)
				.post("/users/2")
				.then()
				.spec(userResponseSpec201Spec)
				.extract()
				.response()
				.as(UserRes.class));
		step("Check response", () -> {
			assertEquals(user.job, job);
			assertEquals(user.name, userName);
			assertNotNull(user.id);
			assertNotNull(user.createdAt);
		});

	}

	@Test
	public void updateUser() {
		UserReq newUser = new UserReq(userName, newJob);
		UserRes user = step("Make request", () -> RestAssured
				.given(requestSpec)
				.when()
				.body(newUser)
				.put("/users/2")
				.then()
				.spec(userResponseSpec)
				.extract()
				.response()
				.as(UserRes.class));
		step("Check response", () -> {
			assertEquals(user.job, newJob);
			assertEquals(user.name, userName);
			assertNotNull(user.updatedAt);
		});
	}

	@Test
	public void deleteUser() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.when()
					.delete("/users/2")
					.then()
					.spec(userResponseSpec204Spec);
		});
	}

	@Test
	public void noFoundUser() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.when()
					.get("/users/23")
					.then()
					.spec(userResponseSpec404Spec);
		});
	}

	@Test
	public void listUser() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.when()
					.contentType(JSON)
					.get("/users?page=2")
					.then()
					.spec(userListResponseSpec)
					.assertThat()
					.body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
		});
	}
}
