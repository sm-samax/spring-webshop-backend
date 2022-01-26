package com.samax.tech.webs;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.samax.tech.webs.comp.ProductController;
import com.samax.tech.webs.entity.Client;
import com.samax.tech.webs.entity.PriceRule;
import com.samax.tech.webs.entity.Product;
import com.samax.tech.webs.entity.ProductToPurchase;
import com.samax.tech.webs.entity.Purchase;
import com.samax.tech.webs.entity.Tag;

@SpringBootApplication()
public class Runner {

	public static void main(String[] args) {
		SpringApplication.run(Runner.class, args);
	}

	@Bean
	public CommandLineRunner insertDataIntoDB(ProductController pc, SessionFactory factory) {
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

			ProductToPurchase p2p1 = new ProductToPurchase(p2, purchase, 6);
			ProductToPurchase p2p2 = new ProductToPurchase(p3, purchase, 3);

			pc.postProduct(p1);
			pc.postProduct(p2);
			pc.postProduct(p3);
			
			Session session = factory.openSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				
				session.save(purchase);
				
				tx.commit();
			} catch (Exception e) {
				if(tx != null) tx.rollback();
			}
			finally {
				session.close();
			}
		};
	}
}
