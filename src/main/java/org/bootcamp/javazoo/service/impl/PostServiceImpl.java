package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.response.PostsFollowedUserDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.IPostRepository;
import org.bootcamp.javazoo.service.interfaces.IPostService;
import org.bootcamp.javazoo.service.interfaces.IProductService;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.MessageDTO;
import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.entity.Product;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {
    IUserService userService;
    IPostRepository postRepository;
    IProductService productService;

    public PostServiceImpl(IUserService userService, IPostRepository postRepository, IProductService productService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.productService = productService;

    }

    @Override
    public List<Post> getPostSorted(List<Post> postsToOrder){
        return postsToOrder.stream()
                .sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
    }
    @Override
    public List<PostDto> getPostsBySeller(int userId){
        List<Seller> sellers = userService.getUserFollowed(userId);
        if(sellers.isEmpty()) throw new NotFoundException("El usuario no sigue a ningun vendedor actualmente");
        return sellers.stream().flatMap(seller1 -> {
            List<Post> postBySeller = getPostSorted(filterPostsByWeeksAgo(2, seller1.getPosts()));
            return postBySeller.stream()
                    .map(this::mapToPostDto);
        }).toList();
    }
    @Override
    public PostsFollowedUserDto getPostsBySellerOfUser(int userId){
        List<PostDto> postDtos = getPostsBySeller(userId);
        return mapToPostsFollowedUserDto(postDtos, userId);
    }
    @Override
    public PostDto mapToPostDto(Post postToMap){
        return new PostDto(
                postToMap.getSeller().getId(),
                postToMap.getId(),
                postToMap.getDate().toString(),
                productService.mapToProductDto(postToMap.getProduct()),
                postToMap.getCategory(),
                postToMap.getPrice());
    }
    @Override
    public PostsFollowedUserDto mapToPostsFollowedUserDto(List<PostDto> postDtos, int userId){
        return new PostsFollowedUserDto(userId, postDtos);
    }

    public List<Post> filterPostsByWeeksAgo(int weeks, List<Post> posts){
        LocalDate weeksAgo = LocalDate.now().minusWeeks(weeks);
        return posts.stream()
                .filter(post -> post.getDate().isAfter(weeksAgo))
                .collect(Collectors.toList());
    }
    @Override
    public MessageDTO addNewPost(PostDto postDto) {
        try{
            postRepository.addNewPost(convertDtoToPost(postDto));
            return new MessageDTO("La publicación se creo exitosamente");
        }catch (RuntimeException e){
            throw new RuntimeException(e + "No se pudo realizar la petición");
        }
    }
    private Post convertDtoToPost(PostDto postDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate datetimnkls = LocalDate.parse(postDto.getDate(), formatter);
        return new Post(
                postDto.getPost_id(),
                (Seller)userService.getUserById(postDto.getUser_id()),
                LocalDate.parse(postDto.getDate(), formatter),
                convertDtoToProduct(postDto.getProduct()),
                postDto.getCategory(),
                postDto.getPrice()
        );
    }
    @Override
    public Product convertDtoToProduct(ProductDto productDto) {
        return new Product(
                productDto.getProduct_id(),
                productDto.getProduct_name(),
                productDto.getType(),
                productDto.getBrand(),
                productDto.getColor(),
                productDto.getNotes()
        );
    }
}
