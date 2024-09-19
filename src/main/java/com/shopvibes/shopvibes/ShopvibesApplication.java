package com.shopvibes.shopvibes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.shopvibes.shopvibes.repository")
public class ShopvibesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopvibesApplication.class, args);
	}
}
