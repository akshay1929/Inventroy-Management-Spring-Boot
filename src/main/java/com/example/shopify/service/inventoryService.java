package com.example.shopify.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopify.domain.Product;
import com.example.shopify.repository.inventoryRepository;

@Service
public class inventoryService {
	@Autowired
    private inventoryRepository repo;
	public List<Product> listAll() {
        return repo.findAll();
    }
    
    public void save(Product std) {
        repo.save(std);
    }
    
    public Product get(long id) {
        return repo.findById(id).get();
    }
    
    public void delete(long id) {
        repo.deleteById(id);
    }
    
    public void update(long id, String productname, String price, String quantity, String description, String type) {
    	Product prod = repo.findById(id).get();
    	prod.setProductname(productname);
    	prod.setPrice(price);
    	prod.setQuantity(quantity);
    	prod.setDescription(description);
    	prod.setType(type);
    	repo.save(prod);
    }
 
}
