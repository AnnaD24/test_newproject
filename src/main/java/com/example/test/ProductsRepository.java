package com.example.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository <Product,Integer> {
}
