package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@Controller
@EnableSwagger2
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

    @PutMapping(value = "/modifyproduct")
    @ResponseBody
    public ResponseEntity<String> modifyProduct(@RequestBody Product product){
        if(productsService.getProduct(product.getId()).isPresent())
            return new ResponseEntity<>(productsService.modifyProduct(product).toString()+" was modified.", HttpStatus.CREATED);
        return new ResponseEntity<>(productsService.modifyProduct(product).toString()+" was created.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteproduct")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@RequestBody Product product){
        if(productsService.getProduct(product.getId()).isPresent()) {
            productsService.deleteProduct(product);
            return new ResponseEntity<>(" Product with id " + product.getId() + " was deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>(product.toString()+" not found", HttpStatus.NOT_FOUND);
    }
}
