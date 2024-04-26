package org.example.productservice.service;

import org.example.productservice.ProductserviceApplication;
import org.example.productservice.dtos.FakeStoreDtos;
import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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

}
