package org.bootcamp.javazoo.service.interfaces;


import org.bootcamp.javazoo.dto.MessageDTO;
import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.dto.response.PostsFollowedUserDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Product;

import java.util.List;

public interface IPostService {
    List<Post> getPostSorted(List<Post> postsToOrder, String order);

    List<PostDto> getPostsBySeller(int userId, String order);

    PostsFollowedUserDto getPostsBySellerOfUser(int userId, String order);

    PostDto mapToPostDto (Post postToMap);

    PostsFollowedUserDto mapToPostsFollowedUserDto (List<PostDto> postDtos, int userId);

    MessageDTO addNewPost(PostDto postDto);

    Product convertDtoToProduct(ProductDto productDto);
}
