package org.example.productservice.controller;

import org.example.productservice.dtos.CreateProductDtos;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.models.ProductTitleAndDesc;
import org.example.productservice.repositories.CategoryRepostories;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final CategoryRepostories categoryRepostories;
    ProductService productService;


    public ProductController(@Qualifier("FakeStoreService") ProductService productService, CategoryRepostories categoryRepostories) {
        this.productService = productService;
        this.categoryRepostories = categoryRepostories;
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


    @GetMapping("/categories")
    public List<Category> getAllCategory()
    {
        return productService.getAllCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody CreateProductDtos createProductDtos)
    {
        return productService.updateProduct(id,
                createProductDtos.getTitle(),
                createProductDtos.getPrice(),
                createProductDtos.getDescription(),
                createProductDtos.getImage(),
                createProductDtos.getCategory()
                );
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable  Long id)
    {
        return productService.deleteProduct(id);
    }

    @DeleteMapping("/products/d")
    public void deleteAllProduct()
    {
        productService.deleteAllProduct();
    }

    @DeleteMapping("/categories/d")
    public void deleteAllCategory()
    {
        productService.deleteAllCategories();
    }

    @GetMapping("/productsOnlyTitleAndDesc/{id}")
    public ProductTitleAndDesc getProductTitleAndDesc(@PathVariable Long id)
    {
        return productService.getProductTitleandDescription(id);
    }

}
