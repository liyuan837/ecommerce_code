package com.liyuan.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class eusercenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(eusercenterApplication.class, args);
	}
}