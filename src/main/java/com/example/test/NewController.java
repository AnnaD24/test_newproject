package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class NewController {
    @Autowired
    ProductsService productsService;

    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<Collection<Product>> getProducts(){
        return new ResponseEntity<>(productsService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    @ResponseBody
    public ResponseEntity getProduct(@PathVariable Integer id){
        if(productsService.getProduct(id).isPresent())
            return new ResponseEntity<>(productsService.getProduct(id).get(), HttpStatus.OK);
        return new ResponseEntity<>("Product with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addproduct")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        //what if I can t add? (eg duplicates not allowed)
        return new ResponseEntity<>(productsService.addProduct(product).toString()+" was added.", HttpStatus.CREATED);
    }
}
