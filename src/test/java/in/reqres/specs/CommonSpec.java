package in.reqres.specs;

import io.restassured.specification.RequestSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class CommonSpec {
	public static RequestSpecification requestSpec = with()
			.log()
			.all()
			.filter(withCustomTemplates())
			.when()
			.contentType(JSON)
			.baseUri("https://reqres.in")
			.basePath("/api");
}
