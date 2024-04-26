package org.example.productservice.controller;


import org.example.productservice.dtos.CreateProductDtos;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductDtos createProductDtos)
    {

       return productService.createProduct(createProductDtos.getTitle(),
                createProductDtos.getPrice(),
                createProductDtos.getDescription(),
                createProductDtos.getImage(),
                createProductDtos.getCategory()
        );

    }

    @GetMapping("/products")
    public List<Product> getAllProduct()
    {
        return productService.getAllProduct();
    }


    @GetMapping("/products/categories")
    public List<Category> getAllCategory()
    {
        return productService.getAllCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id)
    {
        return productService.updateProduct(id);
    }


}
