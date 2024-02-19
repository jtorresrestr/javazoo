package org.bootcamp.javazoo.controller;

import org.bootcamp.javazoo.dto.PostDto;
import org.bootcamp.javazoo.service.impl.PostServiceImpl;
import org.bootcamp.javazoo.service.interfaces.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    IPostService postService;

    public ProductController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> addNewPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.addNewPost(postDto) , HttpStatus.CREATED);
    }
}