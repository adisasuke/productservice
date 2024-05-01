package org.example.productservice.service;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepostories;
import org.example.productservice.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService {

    private ProductRepositories productRepositories;
    private CategoryRepostories categoryRepostories;

    public SelfProductService(ProductRepositories productRepositories,CategoryRepostories categoryRepostories) {
        this.productRepositories = productRepositories;
        this.categoryRepostories = categoryRepostories;
    }
    @Override
    public Product getProductById(Long id) {

        return productRepositories.findByIdIs(id);
    }

    @Override
    public Product createProduct(String title, String price, String description, String image, String category) {

        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

        Category categoryfromdatabase = categoryRepostories.findByTitle(category);
        if(categoryfromdatabase==null){
            categoryfromdatabase = new Category();
            categoryfromdatabase.setTitle(category);
//            categoryfromdatabase = categoryRepostories.save(categoryfromdatabase); cascade.PERSIST will help
        }

        product.setCategory(categoryfromdatabase);

        return productRepositories.save(product);
    }

    @Override
    public List<Product> getAllProduct() {

//        return productRepositories.findAll();
        return productRepositories.getAllProduct();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }
}
