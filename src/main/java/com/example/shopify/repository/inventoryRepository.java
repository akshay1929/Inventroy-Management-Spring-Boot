package com.example.shopify.repository;
import java.util.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopify.domain.Product;

@Repository
public interface  inventoryRepository extends JpaRepository<Product, Long> {
	
}
