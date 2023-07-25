package in.reqres.specs;

import io.restassured.specification.RequestSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class CommonSpec {
	static String baseUri = System.getProperty("baseUrl", "https://reqres.in");
	public static RequestSpecification requestSpec = with()
			.log()
			.all()
			.filter(withCustomTemplates())
			.when()
			.contentType(JSON)
			.baseUri(baseUri)
			.basePath("/api");
}
