package com.mad.repository;

import org.springframework.stereotype.Repository;
import com.mad.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
}
