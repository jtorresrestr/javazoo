package org.bootcamp.javazoo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDto {

    private int productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
