package com.samax.tech.webs.comp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samax.tech.webs.entity.Purchase;
import com.samax.tech.webs.service.PurchaseService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(path = "api/purchases")
public class PurchaseController {

	private final PurchaseService service;
	
	@Autowired
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Purchase>> getPurchases()
	{
		return ResponseEntity.ok(service.getPurchases());
	}
	
	@PostMapping("/post")
	public void postPurchase(@RequestBody Purchase purchase)
	{
		service.postPurchase(purchase);
	}
	
	@DeleteMapping("delete/{id}")
	public long deletePurchaseById(@PathVariable Long id)
	{
		return service.deletePurchaseById(id);
	}
}
