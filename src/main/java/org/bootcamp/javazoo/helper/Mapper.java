package org.bootcamp.javazoo.helper;

import org.bootcamp.javazoo.dto.*;
import org.bootcamp.javazoo.dto.response.PostsFollowedUserDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Product;
import org.bootcamp.javazoo.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Mapper {
    public static UserDto convertUserToUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Post convertDtoToPost(PostDto postDto, Integer postId){
        return new Post(
                postId,
                LocalDate.parse(postDto.getDate(), formatter),
                convertDtoToProduct(postDto.getProduct()),
                postDto.getCategory(),
                postDto.getPrice()
        );
    }
    public static Product convertDtoToProduct(ProductDto productDto) {
        return new Product(
                productDto.getProduct_id(),
                productDto.getProduct_name(),
                productDto.getType(),
                productDto.getBrand(),
                productDto.getColor(),
                productDto.getNotes()
        );
    }
    public static PostResponseDto mapToPostDto(Post postToMap, Integer sellerId){
        return new PostResponseDto(
                sellerId,
                postToMap.getId(),
                postToMap.getDate().toString(),
                mapToProductDto(postToMap.getProduct()),
                postToMap.getCategory(),
                postToMap.getPrice());
    }
    public static PostsFollowedUserDto mapToPostsFollowedUserDto(List<PostResponseDto> postDtos, int userId){
        return new PostsFollowedUserDto(userId, postDtos);
    }
    public static ProductDto mapToProductDto(Product productToMap) {
        return new ProductDto(
                productToMap.getId(),
                productToMap.getName(),
                productToMap.getType(),
                productToMap.getBrand(),
                productToMap.getColor(),
                productToMap.getNotes());
    }

    public static Post mapPromoDtoToPost(PostPromoDto postPromoDto, Integer postId){
        return new Post(
                postId,
                LocalDate.parse(postPromoDto.getDate(), formatter),
                convertDtoToProduct(postPromoDto.getProduct()),
                postPromoDto.getCategory(),
                postPromoDto.getPrice(),
                postPromoDto.isHas_promo(),
                postPromoDto.getDiscount()
        );
    }

}
