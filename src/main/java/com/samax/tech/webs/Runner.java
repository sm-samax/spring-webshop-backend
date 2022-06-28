package com.samax.tech.webs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.samax.tech.webs.client.Client;
import com.samax.tech.webs.pricerule.PriceRule;
import com.samax.tech.webs.product.Product;
import com.samax.tech.webs.product.ProductController;
import com.samax.tech.webs.product.ProductPurchase;
import com.samax.tech.webs.purchase.Purchase;
import com.samax.tech.webs.purchase.PurchaseController;
import com.samax.tech.webs.purchase.PurchaseRepository;
import com.samax.tech.webs.tag.Tag;

@SpringBootApplication()
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	public CommandLineRunner insertDataIntoDB(ProductController pc, PurchaseController purchc) {
		return args -> {

			Tag dressage = new Tag("dressage");
			Tag food = new Tag("food");
			Tag tools = new Tag("tools");

			PriceRule priceRule50 = new PriceRule(new BigDecimal(50.0 / 100.0), true);
			PriceRule priceRule20 = new PriceRule(new BigDecimal(20.0 / 100.0), true);

			Product p1 = new Product("Great drill", "General purpose drill", Set.of(tools), new BigDecimal(124.99),
					101);
			
			Product p2 = new Product("Beer", "Light beer", "linkToBeerImage", Set.of(food), new BigDecimal(2.99),
					priceRule20, 1333);
			
			Product p3 = new Product("Jeans", "Description of jeans", "linkToJeansImage", Set.of(dressage),
					new BigDecimal(55.01), priceRule50, 6);

			Client client = new Client("Robert", "Miller", LocalDate.of(1989, 3, 3), "Random street - 777",
					"Nothingham", "State", "111-222");

			Purchase purchase = new Purchase(client, Currency.getInstance(Locale.US));

			ProductPurchase pp1 = new ProductPurchase(p2, purchase, 6);
			ProductPurchase pp2 = new ProductPurchase(p3, purchase, 3);
			
			pc.postProduct(p1);
			pc.postProduct(p2);
			pc.postProduct(p3);
			
			purchc.postPurchase(purchase);
		};
	}
}
