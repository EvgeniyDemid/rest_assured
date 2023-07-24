package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsNull.notNullValue;

public class UserSpec {
	public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("job",notNullValue())
			.expectBody("name",notNullValue())
			.expectBody("updatedAt",notNullValue())
			.build();
	public static ResponseSpecification userResponseSpec201Spec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(201)
			.expectBody("name",notNullValue())
			.expectBody("job",notNullValue())
			.expectBody("id",notNullValue())
			.expectBody("createdAt",notNullValue())
			.build();
	public static ResponseSpecification userResponseSpec204Spec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(204)
			.build();
	public static ResponseSpecification userResponseSpec404Spec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(404)
			.build();
	public static ResponseSpecification userListResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("page",notNullValue())
			.expectBody("per_page",notNullValue())
			.expectBody("total",notNullValue())
			.expectBody("total_pages",notNullValue())
			.expectBody("data",notNullValue())
			.build();
}
