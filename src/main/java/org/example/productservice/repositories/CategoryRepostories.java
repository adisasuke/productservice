package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepostories extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    Category save(Category category);
}
