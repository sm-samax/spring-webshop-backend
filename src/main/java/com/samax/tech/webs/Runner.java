package com.samax.tech.webs;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import com.samax.tech.webs.comp.ProductController;
import com.samax.tech.webs.entity.PriceRule;
import com.samax.tech.webs.entity.Product;

@SpringBootApplication()
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}
	
	@Bean
	public CommandLineRunner test(ProductController pc)
	{
		return args -> {
			
			Product pillow = new Product("Pillow", //name
					"It's a good pillow", //description
					"https://rnb.scene7.com/is/image/roomandboard/151908?size=675,400&scl=1&$prodzoom0$&size=675,400&scl=1", //thumbnail URL
					new String[]{"https://rnb.scene7.com/is/image/roomandboard/151908?size=675,400&scl=1&$prodzoom0$&size=675,400&scl=1"}, //URL-s of product's images
					new String[] {"decoration, sleeping room"}, //tags
					new BigDecimal(1200), //price
					true, //whether price rule presents
					new PriceRule(50.0, true), // price rule
					true, //available
					999); //amount left
			
			Product jeans = new Product("Farmer jeans", "Trendy pant",
					null,
					null,
					new String[] {"fashion, pant, jeans"},
					new BigDecimal(3500), true, new PriceRule(20.0, true), true, 999);
			
			Product sunGlasses = new Product("Sunglasses", "Cool sunglasses",
					null,
					null,
					new String[] {"fashion, glasses"},
					new BigDecimal(800), true, 999);
			
			URI uri = URI.create("/products");
			
			pc.putProduct(new RequestEntity<Product>(pillow, HttpMethod.PUT, uri));
			pc.putProduct(new RequestEntity<Product>(jeans, HttpMethod.PUT, uri));
			pc.putProduct(new RequestEntity<Product>(sunGlasses, HttpMethod.PUT, uri));
			
			//pc handles the inappropriate product objects
			pc.putProduct(new RequestEntity<Product>(new Product(), HttpMethod.PUT, uri));
		};
	}

}
