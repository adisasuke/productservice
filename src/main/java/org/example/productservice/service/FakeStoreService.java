package org.example.productservice.service;

import org.example.productservice.ProductserviceApplication;
import org.example.productservice.dtos.CategoryDtos;
import org.example.productservice.dtos.CreateProductDtos;
import org.example.productservice.dtos.FakeStoreDtos;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreService implements ProductService {


    public RestTemplate restTemplate;

    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        FakeStoreDtos fakeStoreDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreDtos.class);

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


}
