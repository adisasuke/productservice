package org.example.productservice.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDtos {

    String title;
    String price;
    String description;
    String image;
    String category;

}
