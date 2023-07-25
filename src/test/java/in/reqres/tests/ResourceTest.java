package in.reqres.tests;

import in.reqres.pojo.resource.ResourceListReq;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static in.reqres.specs.CommonSpec.requestSpec;
import static in.reqres.specs.ResourceSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResourceTest {

	@Test
	public void listResource() {
		ResourceListReq listResource =	step("Make request", () ->
			RestAssured
					.given(requestSpec)
					.get("/unknown")
					.then()
					.spec(resourceListResponseSpec)
					.body(matchesJsonSchemaInClasspath("schemas/resource-list-schema.json"))
					.extract()
					.response()
					.as(ResourceListReq.class));
		step("Check response", () -> {
			assertEquals(listResource.getPage(),1);
			assertEquals(listResource.getPerPage(),6);
			assertEquals(listResource.getTotal(),12);
			assertEquals(listResource.getTotalPages(),2);
			assertEquals(listResource.getData().get(0).getId(),1);
			assertEquals(listResource.getData().get(0).getName(),"cerulean");
			assertEquals(listResource.getData().get(0).getYear(),2000);
			assertEquals(listResource.getData().get(0).getColor(),"#98B2D1");
			assertEquals(listResource.getData().get(0).getPantoneValue(),"15-4020");
			assertEquals(listResource.getSupport().getUrl(),"https://reqres.in/#support-heading");
			assertEquals(listResource.getSupport().getText(),"To keep ReqRes free, contributions towards server costs are appreciated!");
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
