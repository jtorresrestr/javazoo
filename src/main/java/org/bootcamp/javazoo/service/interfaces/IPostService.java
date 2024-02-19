package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.response.PostsFollowedUserDto;
import org.bootcamp.javazoo.entity.Post;

import java.util.List;

public interface IPostService {
    List<Post> getPostSorted(List<Post> postsToOrder);

    List<PostDto> getPostsBySeller(int userId);

    PostsFollowedUserDto getPostsBySellerOfUser(int userId);

    PostDto mapToPostDto (Post postToMap);

    PostsFollowedUserDto mapToPostsFollowedUserDto (List<PostDto> postDtos, int userId);
}
