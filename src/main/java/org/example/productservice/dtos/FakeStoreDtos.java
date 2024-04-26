package org.example.productservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.productservice.models.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreDtos {
    Long id;
    String title;
    String price;
    String category;
    String description;
    String image;

    public Product toProduct()
    {
        Product product = new Product();

        product.setTitle(title);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setImage(image);
        return product;
    }

}
