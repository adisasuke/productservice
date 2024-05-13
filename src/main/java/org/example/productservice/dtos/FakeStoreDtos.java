package org.example.productservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreDtos {
    private String id;
    private String title;
    private String price;
    private String category;
    private String description;
    private String image;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(Long.valueOf(id));
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

        Category category = new Category();
        category.setTitle(title);
        product.setCategory(category);
        return product;
    }

}
