package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.ListQueryByExampleExecutor;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findByIdIs(long id);

    List<Product> findAll();

    @Query(value = "select * from product",nativeQuery = true)
    List<Product> getAllProduct();

}
