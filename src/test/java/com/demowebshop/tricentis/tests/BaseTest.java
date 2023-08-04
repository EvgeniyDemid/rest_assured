package com.demowebshop.tricentis.tests;

import com.codeborne.selenide.Configuration;
import com.demowebshop.tricentis.config.WebConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.demowebshop.tricentis.specs.CommonSpec.requestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class BaseTest {
	static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
	String authCookieValue;
	String authCookieKey = "NOPCOMMERCE.AUTH";
	String requestVerificationTokenValue;
	String requestVerificationTokenKey = "__RequestVerificationToken";
	private final String login = config.userEmail();
	private final String password = config.password();

	@BeforeAll
	static void beforeAll() {
		Configuration.baseUrl = config.baseUrl();
		Configuration.browserSize = config.browserSize();
		RestAssured.baseURI = config.baseUrl();
	}
	@BeforeEach
	void login() {
		step("Get authorization cookie by api and set it to browser", () -> {
			authCookieValue = given(requestSpec)
					.formParam("Email", login)
					.formParam("Password", password)
					.when()
					.post("/login")
					.then()
					.log().all()
					.statusCode(302)
					.extract()
					.cookie(authCookieKey);
			requestVerificationTokenValue = given(requestSpec)
					.cookie(authCookieKey, authCookieValue)
					.when()
					.get("/customer/info")
					.then()
					.log().all()
					.statusCode(200)
					.extract()
					.cookie(requestVerificationTokenKey);
		});
	}
}
