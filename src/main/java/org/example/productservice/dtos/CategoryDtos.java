package org.example.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.productservice.models.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtos {

    String category;

    public Category toCategory()
    {
        Category cg = new Category();
        cg.setCategory(this.category);
        return cg;
    }
}
