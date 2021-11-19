package com.samax.tech.webs;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import com.samax.tech.webs.comp.ProductController;
import com.samax.tech.webs.entity.PriceRule;
import com.samax.tech.webs.entity.Product;
import com.samax.tech.webs.entity.Tag;

@SpringBootApplication()
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	public CommandLineRunner test(ProductController pc) {
		return args -> {

			Tag dressage = new Tag("dressage");
			Tag nap = new Tag("nap");
			Tag pant = new Tag("pant");

			Product pillow = new Product("Pillow", // name
					"Description of a good pillow", // description
					"thumbnailURL", // thumbnail
					new String[] {"imageURL1", "imageURL2"}, // URL-s
					Set.of(nap), // tags
					new BigDecimal(1200), // price
					new PriceRule(50.0, true), // price rule
					999, //amount left
					102); // popularity

			Product jeans = new Product("Farmer jeans",
					"Trendy pant",
					"thumbnailURL",
					new String[] {"imageURL1", "imageURL2"},
					Set.of(dressage, pant),
					new BigDecimal(3500),
					new PriceRule(20.0, true),
					999,
					566);

			Product sunGlasses = new Product("Sunglasses", "Cool sunglasses", Set.of(dressage),
					new BigDecimal(800), 999);
			
			URI uri = URI.create("http://localhost:8080/products/put");
			
			pc.postProduct(new RequestEntity<Product>(pillow, HttpMethod.PUT, uri));
			pc.postProduct(new RequestEntity<Product>(jeans, HttpMethod.PUT, uri));
			pc.postProduct(new RequestEntity<Product>(sunGlasses, HttpMethod.PUT, uri));
		};
	}
}
