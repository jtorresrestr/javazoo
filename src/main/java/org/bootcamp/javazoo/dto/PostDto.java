package org.bootcamp.javazoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private Integer user_id;
    private String date;
    private ProductDto product;
    private Integer category;
    private Double price;

}
