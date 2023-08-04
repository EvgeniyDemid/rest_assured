package com.demowebshop.tricentis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demowebshop.tricentis.specs.CommonSpec.requestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ShoppingCartTests extends BaseTest {
	private final String pathUrl = "addproducttocart/catalog/13/1/1";
	@AfterEach
	public void deleteBookInCart() {
		open("/cart");
		$("[name='removefromcart']").click();
		$("[name='updatecart']").click();
		$("#topcartlink").shouldHave(text("(0)"));
	}

	@Test
	public void addBookInCart() {
		step("add book in cart", () -> {
			given(requestSpec)
					.cookie(authCookieKey, authCookieValue)
					.cookie(requestVerificationTokenKey, requestVerificationTokenValue)
					.when()
					.post(pathUrl)
					.then()
					.log().all()
					.statusCode(200)
					.body("success", is(true))
					.body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
					.body("updatetopcartsectionhtml", is("(1)"));
		});
		step("Open main page ", () -> {
			open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
			Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
			getWebDriver().manage().addCookie(authCookie);
			open("");
		});
		step("check number book in cart", () -> {
			$("#topcartlink").shouldHave(text("(1)"));
		});
		step("add book in cart", () -> {
			given(requestSpec)
					.cookie(authCookieKey, authCookieValue)
					.cookie(requestVerificationTokenKey, requestVerificationTokenValue)
					.when()
					.post(pathUrl)
					.then()
					.log().all()
					.statusCode(200)
					.body("success", is(true))
					.body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
					.body("updatetopcartsectionhtml", is("(2)"));
		});
		step("Open main page ", () -> {
			open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
			Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
			getWebDriver().manage().addCookie(authCookie);
			open("");
		});
		step("check number book in cart", () -> {
			$("#topcartlink").shouldHave(text("(2)"));
		});
	}
}
