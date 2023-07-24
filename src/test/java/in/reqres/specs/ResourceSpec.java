package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.core.IsNull.notNullValue;

public class ResourceSpec {
	public static ResponseSpecification resourceListResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("page",notNullValue())
			.expectBody("per_page",notNullValue())
			.expectBody("total",notNullValue())
			.expectBody("total_pages",notNullValue())
			.expectBody("data",notNullValue())
			.build();
	public static ResponseSpecification resourceSingleResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("data.id",notNullValue())
			.expectBody("data.name",notNullValue())
			.expectBody("data.year",notNullValue())
			.expectBody("data.color",notNullValue())
			.expectBody("data.pantone_value",notNullValue())
			.expectBody("support.url",notNullValue())
			.expectBody("support.text",notNullValue())
			.build();
	public static ResponseSpecification resourceNotFoundResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(404)
			.build();
}
