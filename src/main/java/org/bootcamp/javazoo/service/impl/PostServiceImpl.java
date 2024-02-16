package org.bootcamp.javazoo.service.impl;

import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.MessageDTO;
import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.dto.ProductDto;
import org.bootcamp.javazoo.entity.Post;
import org.bootcamp.javazoo.entity.Product;
import org.bootcamp.javazoo.repository.impl.PostRepositoryImpl;
import org.bootcamp.javazoo.repository.interfaces.IPostRepository;
import org.bootcamp.javazoo.service.interfaces.IPostService;
import org.bootcamp.javazoo.service.interfaces.IProductService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements IPostService, IProductService {

    IProductService productService;
    IPostRepository postRepository;

    public PostServiceImpl(ProductServiceImpl productService, PostRepositoryImpl postRepository){
        this.productService = productService;
        this.postRepository = postRepository;
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
        return new Post(
                postDto.getUser_id(),
                postDto.getDate(),
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
