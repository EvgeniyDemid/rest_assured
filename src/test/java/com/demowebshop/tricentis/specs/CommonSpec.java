package com.demowebshop.tricentis.specs;

import com.demowebshop.tricentis.config.WebConfig;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class CommonSpec {
	static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

	public static RequestSpecification requestSpec = with()
			.log()
			.all()
			.filter(withCustomTemplates())
			.when()
			.contentType("application/x-www-form-urlencoded")
			.baseUri(config.baseUrl());
}
