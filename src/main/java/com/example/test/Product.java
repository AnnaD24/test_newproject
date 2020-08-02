package com.example.test;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "products", schema = "prods")
public class Product {
    @Id
    private Integer id;
    private String name;
    private String size;
    private double price;
    private String producer;
}
