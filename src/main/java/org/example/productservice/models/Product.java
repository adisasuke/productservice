package org.example.productservice.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{

    private String title;
    private String price;
    private String description;
    private String image;

    private Category category;

}
