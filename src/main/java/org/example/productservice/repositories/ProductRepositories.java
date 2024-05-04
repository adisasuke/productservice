package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.projections.productTitleandDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.ListQueryByExampleExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findByIdIs(long id);

    List<Product> findAll();

    @Query(value = "select * from product",nativeQuery = true)
    List<Product> getAllProduct();


    void deleteById(long id);

    void deleteAll();

    @Query(value = "select * from product where id = :id", nativeQuery = true)
    productTitleandDescription findTitleAndDescById(@Param("id") Long id);


}
