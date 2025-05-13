package com.jpa.exmple;

import com.jpa.exmple.entity.Product;
import com.jpa.exmple.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaExamplesApplication implements CommandLineRunner
{


    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaExamplesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product = new Product();


        product.setId(101);
        product.setTitle("Laptop");
        product.setPrice(50000);
        product.setDescription("Dell Inspiron");
        product.setLive(true);
        Product savedProduct = productRepository.save(product);
        System.out.println("Product saved with ID: " + savedProduct.getId());
    }
}
