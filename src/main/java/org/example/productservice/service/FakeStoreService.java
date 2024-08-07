package org.example.productservice.service;

import org.example.productservice.ProductserviceApplication;
import org.example.productservice.dtos.CategoryDtos;
import org.example.productservice.dtos.CreateProductDtos;
import org.example.productservice.dtos.FakeStoreDtos;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.models.ProductTitleAndDesc;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;



@Service("FakeStoreService")
public class FakeStoreService implements ProductService {

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        Product product = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
        if (product != null) {return product;}

        FakeStoreDtos fakeStoreDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreDtos.class);

        redisTemplate.opsForValue().set(String.valueOf(id), fakeStoreDtos.toProduct());

        return fakeStoreDtos.toProduct();
    }

    @Override
    public Product createProduct(String title, String price, String description, String image, String category) {

        FakeStoreDtos fakeStoreDtos = new FakeStoreDtos();
        fakeStoreDtos.setTitle(title);
        fakeStoreDtos.setPrice(price);
        fakeStoreDtos.setDescription(description);
        fakeStoreDtos.setImage(image);
        fakeStoreDtos.setCategory(category);

        FakeStoreDtos fk = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreDtos,FakeStoreDtos.class);

        return fk.toProduct();
    }

    @Override
    public List<Product> getAllProduct() {



        ResponseEntity<FakeStoreDtos[]> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreDtos[].class);

        FakeStoreDtos[] fakeStoreDtos = response.getBody();

        List<Product> products = new ArrayList<>();

        for (FakeStoreDtos fakeStoreDto : fakeStoreDtos) {
            products.add(fakeStoreDto.toProduct());
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {

        ResponseEntity<CategoryDtos[]> response =
        restTemplate.getForEntity("https://fakestoreapi.com/products/categories", CategoryDtos[].class);

        CategoryDtos[] categoryDtos = response.getBody();

        List<Category> categories = new ArrayList<>();
        for(CategoryDtos categoryDto : categoryDtos){
            categories.add(categoryDto.toCategory());
        }

        return categories;

    }

    @Override
    public Product updateProduct(Long id,String title, String price, String description, String image, String category) {

        Product p = getProductById(id);

        FakeStoreDtos fakeStoreDtos = new FakeStoreDtos();

        fakeStoreDtos.setTitle(title);
        fakeStoreDtos.setPrice(price);
        fakeStoreDtos.setDescription(description);
        fakeStoreDtos.setImage(image);
        fakeStoreDtos.setCategory(category);
        fakeStoreDtos.setId(String.valueOf(id));


        ResponseEntity<FakeStoreDtos> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(fakeStoreDtos),
                FakeStoreDtos.class
        );

        FakeStoreDtos fakeStoreDtos1 = response.getBody();

        return fakeStoreDtos1.toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {

        Product p = getProductById(id);
        FakeStoreDtos fakeStoreDtos = new FakeStoreDtos();



        ResponseEntity<FakeStoreDtos> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                new HttpEntity<>(fakeStoreDtos),
                FakeStoreDtos.class
        );

        FakeStoreDtos fakeStoreDtos1 = response.getBody();

        return fakeStoreDtos1.toProduct();

    }

    @Override
    public void deleteAllProduct() {
    // Not relevant for third party API
    }

    @Override
    public void deleteAllCategories() {
    //Not relevant for third party API
    }

    @Override
    public ProductTitleAndDesc getProductTitleandDescription(Long id) {
        //This method is not relevant for third party api
        return null;
    }

}




