package com.demowebshop.tricentis.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demowebshop.tricentis.specs.CommonSpec.requestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class ProfileEditTest extends BaseTest {
	Faker faker = new Faker(new Locale("en"));
	private final String pathUrl = "customer/addressadd";

	@Test
	public void addAddressesInCustomerInfo() {
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = faker.internet().emailAddress();
		String company = String.valueOf(faker.company());
		String city = faker.address().city();
		String address1 = faker.address().streetAddress();
		String address2 = faker.address().streetAddress();
		String zipPostalCode = faker.address().zipCode();
		String phoneNumber = faker.phoneNumber().phoneNumber();
		String faxNumber = faker.phoneNumber().phoneNumber();
		step("edit Customer info by API", () -> {
			given(requestSpec)
					.formParam("Address.Id", "0")
					.formParam("Address.FirstName", firstName)
					.formParam("Address.LastName", lastName)
					.formParam("Address.Email", email)
					.formParam("Address.Company", company)
					.formParam("Address.CountryId", "1")
					.formParam("Address.StateProvinceId", "1")
					.formParam("Address.City", city)
					.formParam("Address.Address1", address1)
					.formParam("Address.Address2", address2)
					.formParam("Address.ZipPostalCode", zipPostalCode)
					.formParam("Address.PhoneNumber", phoneNumber)
					.formParam("Address.FaxNumber", faxNumber)
					.cookie(authCookieKey, authCookieValue)
					.cookie(requestVerificationTokenKey, requestVerificationTokenValue)
					.when()
					.post(pathUrl)
					.then()
					.log().all()
					.statusCode(302);
		});
		step("Open customer info ", () -> {
			open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
			Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
			getWebDriver().manage().addCookie(authCookie);
			open("/customer/addresses");
		});
		step("Check customer info ", () -> {
			$$(".info").last().$(".name").shouldHave(text(firstName + " " + lastName));
			$$(".info").last().$(".email").shouldHave(text(email));
			$$(".info").last().$(".phone").shouldHave(text(phoneNumber));
			$$(".info").last().$(".fax").shouldHave(text(faxNumber));
			$$(".info").last().$(".company").shouldHave(text(company));
			$$(".info").last().$(".address1").shouldHave(text(address1));
			$$(".info").last().$(".address2").shouldHave(text(address2));
			$$(".info").last().$(".city-state-zip").shouldHave(text(city));
			$$(".info").last().$(".country").shouldHave(text("United States"));
		});
	}
}
