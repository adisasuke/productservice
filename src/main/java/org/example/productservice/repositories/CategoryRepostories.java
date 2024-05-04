package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepostories extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    List<Category> findAll();

    void deleteAll();


}
