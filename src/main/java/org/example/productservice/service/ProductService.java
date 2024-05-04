package org.example.productservice.service;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.models.ProductTitleAndDesc;

import java.util.List;


public interface ProductService {

    public Product getProductById(Long id);
    public Product createProduct(String title, String price, String description, String image, String category);
    public List<Product> getAllProduct();
    public List<Category> getAllCategories();
    public Product updateProduct(Long id,String title, String price, String description, String image, String category);
    public Product deleteProduct(Long id);
    public void deleteAllProduct();
    public void deleteAllCategories();
    public ProductTitleAndDesc getProductTitleandDescription(Long id);
}
