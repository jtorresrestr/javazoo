package org.bootcamp.javazoo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseDto {
    @NotNull
    @Min(0)
    private Integer user_id;

    @NotNull
    @Min(0)
    private Integer post_id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String date;

    @NotNull
    private ProductDto product;

    @NotNull
    private Integer category;

    @NotNull
    @Min(0)
    private Double price;

    @JsonDeserialize(using = NumberDeserializers.BooleanDeserializer.class)
    private Boolean has_promo;

    @Min(0)
    @Max(1)
    private Double discount;

    public PostResponseDto(Integer user_id, Integer post_id, String date, ProductDto product, Integer category, Double price) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        String string = "{" +
                "user_id=" + user_id +
                ", post_id=" + post_id +
                ", date='" + date + '\'' +
                ", product=" + product +
                ", category=" + category +
                ", price=" + price +
                "}";
        if (has_promo != null && has_promo) {
            string = "has_promo=" + has_promo +
                    ", discount=" + discount;
        }
        return string;
    }
}
