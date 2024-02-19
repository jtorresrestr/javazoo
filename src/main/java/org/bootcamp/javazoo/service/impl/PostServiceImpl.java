package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.impl.SellerRepositoryImpl;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Product;
import org.bootcamp.javazoo.repository.impl.PostRepositoryImpl;
import org.bootcamp.javazoo.repository.interfaces.IPostRepository;
import org.bootcamp.javazoo.service.interfaces.IPostService;
import org.bootcamp.javazoo.service.interfaces.IProductService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl implements IPostService, IProductService {

    IProductService productService;
    IPostRepository postRepository;
    ISellerRepository sellerRepository;

    public PostServiceImpl(ProductServiceImpl productService, PostRepositoryImpl postRepository, SellerRepositoryImpl sellerRepository){
        this.productService = productService;
        this.postRepository = postRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public MessageDto addNewPost(PostDto postDto) {

        Seller seller = sellerRepository.findById(postDto.getUser_id());
        if(seller == null){
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
        return new Post(
                postDto.getUser_id(),
                sellerRepository.findById(postDto.getUser_id()),
                getDateFormat(postDto.getDate()),
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
