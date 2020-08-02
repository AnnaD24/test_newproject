package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;


    public Product addProduct(Product product) {
        return productsRepository.save(product);
    }

    public Collection<Product> getProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> findProduct(Integer id){
        return productsRepository.findById(id);
    }

    public Optional<Product> getProduct(Integer id){
        return productsRepository.findById(id);
    }
}
