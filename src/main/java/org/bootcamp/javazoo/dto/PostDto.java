package org.bootcamp.javazoo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bootcamp.javazoo.entity.User;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class PostDto {

    private int userId;
    private int postId;
    private String date; // "date": "01-05-2021",
    private ProductDto product;
    private int category;
    private double price;
}
