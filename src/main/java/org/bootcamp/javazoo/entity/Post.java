package org.bootcamp.javazoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    private Integer id;
    private String date;
    private Product product;
    private Integer category;
    private Double price;
}
