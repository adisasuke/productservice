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
public class CreateProductDtos {

    private String title;
    private String price;
    private String description;
    private String image;
    private Category category;

}
