package in.reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsNull.notNullValue;

public class RegisterSpec {
	public static ResponseSpecification registerResponseSpec = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("token",notNullValue())
			.build();
	public static ResponseSpecification registerResponseSpecFindUsers = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(200)
			.expectBody("page",notNullValue())
			.expectBody("per_page",notNullValue())
			.expectBody("total",notNullValue())
			.expectBody("total_pages",notNullValue())
			.expectBody("data",notNullValue())
			.build();
	public static ResponseSpecification registerResponseSpec400Error = new ResponseSpecBuilder()
			.log(ALL)
			.expectStatusCode(400)
			.expectBody("error",notNullValue())
			.build();
}
