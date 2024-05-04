package org.example.productservice.service;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.models.ProductTitleAndDesc;
import org.example.productservice.projections.productTitleandDescription;
import org.example.productservice.repositories.CategoryRepostories;
import org.example.productservice.repositories.ProductRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

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
        return categoryRepostories.findAll();
    }

    @Override
    public Product updateProduct(Long id,String title, String price, String description, String image, String category) {

        Logger log = Logger.getLogger("SelfProductService");

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

        Category categoryfromdatabase = categoryRepostories.findByTitle(category);
        if(categoryfromdatabase==null){
            categoryfromdatabase = new Category();
            categoryfromdatabase.setTitle(category);
            log.info("created new category");
            categoryfromdatabase = categoryRepostories.save(categoryfromdatabase);// cascade.PERSIST will help
            //If above line is commented then update not works due to some foreign key constraint
        }

        log.info("category set with value " + categoryfromdatabase.getTitle());
        product.setCategory(categoryfromdatabase);

        return productRepositories.save(product);

    }

    @Override
    public Product deleteProduct(Long id) {

        Product product = productRepositories.findByIdIs(id);
        productRepositories.deleteById(id);
        return product;
    }

    @Override
    public void deleteAllProduct() {
        productRepositories.deleteAll();
    }

    @Override
    public void deleteAllCategories() {
        categoryRepostories.deleteAll();
    }

    @Override
    public ProductTitleAndDesc getProductTitleandDescription(Long id) {

        productTitleandDescription prod = productRepositories.findTitleAndDescById(id);

        ProductTitleAndDesc product = new ProductTitleAndDesc();
        product.setTitle(prod.getTitle());
        product.setDescription(prod.getDescription());

        return product;
    }
}
