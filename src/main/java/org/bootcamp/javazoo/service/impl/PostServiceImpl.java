package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.PostResponseDto;
import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.response.PostsFollowedUserDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.repository.interfaces.IPostRepository;
import org.bootcamp.javazoo.service.interfaces.IPostService;
import org.bootcamp.javazoo.service.interfaces.IProductService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.entity.Product;


import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {
    IUserService userService;
    IPostRepository postRepository;
    IProductService productService;
    ISellerService sellerService;

    public PostServiceImpl(IUserService userService, IPostRepository postRepository, IProductService productService, ISellerService sellerService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.productService = productService;
        this.sellerService = sellerService;

    }

    @Override
    public List<Post> getPostSorted(List<Post> postsToOrder){
        return postsToOrder.stream()
                .sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
    }
    @Override
    public List<PostResponseDto> getPostsBySeller(int userId){
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
        List<PostResponseDto> postDtos = getPostsBySeller(userId);
        return mapToPostsFollowedUserDto(postDtos, userId);
    }
    @Override
    public PostResponseDto mapToPostDto(Post postToMap){
        return new PostResponseDto(
                postToMap.getSeller().getId(),
                postToMap.getId(),
                postToMap.getDate().toString(),
                productService.mapToProductDto(postToMap.getProduct()),
                postToMap.getCategory(),
                postToMap.getPrice());
    }
    @Override
    public PostsFollowedUserDto mapToPostsFollowedUserDto(List<PostResponseDto> postDtos, int userId){
        return new PostsFollowedUserDto(userId, postDtos);
    }

    public List<Post> filterPostsByWeeksAgo(int weeks, List<Post> posts){
        LocalDate weeksAgo = LocalDate.now().minusWeeks(weeks);
        return posts.stream()
                .filter(post -> post.getDate().isAfter(weeksAgo))
                .collect(Collectors.toList());
    }
    @Override
    public MessageDto addNewPost(PostDto postDto) {
        Seller seller = sellerService.getById(postDto.getUser_id());
        if(seller == null) {
            throw new NotFoundException("Seller not found");
        }

        Post post = convertDtoToPost(postDto);
        postRepository.addNewPost(post);
        List<Post> postList = seller.getPosts();
        postList.add(post);
        seller.setPosts(postList);
        return new MessageDto("The publication was created successfully");
    }
    private Post convertDtoToPost(PostDto postDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new Post(
                postDto.getUser_id(),
                sellerService.getById(postDto.getUser_id()),
                LocalDate.parse(postDto.getDate(), formatter),
                convertDtoToProduct(postDto.getProduct()),
                postDto.getCategory(),
                postDto.getPrice()
        );
    }

    private static LocalDate getDateFormat(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
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
