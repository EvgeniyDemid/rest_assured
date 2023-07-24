package in.reqres.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CommonSpec.requestSpec;
import static in.reqres.specs.ResourceSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


public class ResourceTest {

	@Test
	public void listResource() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.get("/unknown")
					.then()
					.spec(resourceListResponseSpec)
					.body(matchesJsonSchemaInClasspath("schemas/resource-list-schema.json"))
					.body("page", equalTo(1))
					.body("per_page", equalTo(6))
					.body("total", equalTo(12))
					.body("total_pages", equalTo(2));
		});
	}

	@Test
	public void singleResource() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.get("/unknown/2")
					.then()
					.spec(resourceSingleResponseSpec)
					.body("data.id", equalTo(2))
					.body("data.name", equalTo("fuchsia rose"))
					.body("data.year", equalTo(2001))
					.body("data.color", equalTo("#C74375"))
					.body("data.pantone_value", equalTo("17-2031"));
		});
	}

	@Test
	public void singleResourceNoFound() {
		step("Make request", () -> {
			RestAssured
					.given(requestSpec)
					.get("/unknown/23")
					.then()
					.spec(resourceNotFoundResponseSpec);
		});
	}
}
