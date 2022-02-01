package com.samax.tech.webs.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samax.tech.webs.entity.Purchase;
import com.samax.tech.webs.repos.PurchaseRepository;

@Service
public class PurchaseService {

	private final SessionFactory factory;
	private final PurchaseRepository repository;
	
	@Autowired
	public PurchaseService(SessionFactory factory, PurchaseRepository repository) {
		this.factory = factory;
		this.repository = repository;
	}
	
	public void postPurchase(Purchase purchase)
	{
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
	}

	public List<Purchase> getPurchases() {
		return repository.findAll();
	}

	public long deletePurchaseById(Long id) {
		return repository.deletePurchaseById(id);
	}
}
