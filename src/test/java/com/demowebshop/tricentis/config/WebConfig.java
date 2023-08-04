package com.demowebshop.tricentis.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
		"system:properties",
		"classpath:config/local.properties"
})
public interface WebConfig extends Config {

	@Key("browserSize")
	@DefaultValue("1920x1080")
	String browserSize();

	@Key("baseUrl")
	@DefaultValue("https://demowebshop.tricentis.com")
	String baseUrl();

	@Key("userEmail")
	String userEmail();

	@Key("password")
	String password();
}
