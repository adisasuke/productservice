package org.example.productservice.controller;

import org.example.productservice.Exception.InvalidTokenException;
import org.example.productservice.authentication.AuthenticationCommon;
import org.example.productservice.dtos.CreateProductDtos;
import org.example.productservice.dtos.UserDtos;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.models.ProductTitleAndDesc;
import org.example.productservice.repositories.CategoryRepostories;
import org.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ProductController {

    private final CategoryRepostories categoryRepostories;
    private ProductService productService;
    private AuthenticationCommon authenticationCommon;

    public ProductController(@Qualifier("FakeStoreService") ProductService productService, CategoryRepostories categoryRepostories, AuthenticationCommon authenticationCommon) {
        this.productService = productService;
        this.categoryRepostories = categoryRepostories;
        this.authenticationCommon = authenticationCommon;
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
    public List<Product> getAllProduct(@RequestHeader("Authorization") String token) throws InvalidTokenException {
        Logger.getAnonymousLogger().info("token value is :  " + token);

        UserDtos userDtos;
        try {
            userDtos = authenticationCommon.validate(token);
        }
        catch (Exception e)
        {
            Logger.getAnonymousLogger().info("Exception in request validation");
            return null;
        }
        if(userDtos.getEmail() == null)
        {
            throw new InvalidTokenException();
        }

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
