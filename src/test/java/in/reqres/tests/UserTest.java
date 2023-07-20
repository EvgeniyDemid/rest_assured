package in.reqres.tests;

import in.reqres.pojo.User.UserReq;
import in.reqres.pojo.User.UserRes;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {
	String userName = "morpheus";
	String newJob = "zion resident";
	String job = "leader";

	@BeforeAll
	static public void setup() {
		RestAssured.baseURI = "https://reqres.in/api";
	}

	@Test
	public void createUser() {
		UserReq newUser = new UserReq(userName, job);
		UserRes user = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(newUser)
				.post("/users/2")
				.then()
				.log()
				.all()
				.statusCode(201)
				.extract()
				.response()
				.as(UserRes.class);

		assertEquals(user.job, job);
		assertEquals(user.name, userName);
		assertNotNull(user.id);
		assertNotNull(user.createdAt);
	}

	@Test
	public void updateUser() {
		UserReq newUser = new UserReq(userName, newJob);
		UserRes user = RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.body(newUser)
				.put("/users/2")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.response()
				.as(UserRes.class);

		assertEquals(user.job, newJob);
		assertEquals(user.name, userName);
		assertNotNull(user.updatedAt);
	}

	@Test
	public void deleteUser() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.delete("/users/2")
				.then()
				.log()
				.all()
				.statusCode(204);

	}
	@Test
	public void noFoundUser() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/users/23")
				.then()
				.log()
				.all()
				.statusCode(404);

	}

	@Test
	public void listUser() {
		RestAssured
				.given()
				.log()
				.all()
				.when()
				.contentType(JSON)
				.get("/users?page=2")
				.then()
				.log()
				.all()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("user-schema.json"));

	}
}
