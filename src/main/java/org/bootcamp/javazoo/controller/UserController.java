package org.bootcamp.javazoo.controller;

import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final ISellerService sellerService;

    public UserController(IUserService userService, ISellerService sellerService) {
        this.userService = userService;
        this.sellerService = sellerService;
    }
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersListDto> getFollowersList(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return ResponseEntity.ok(sellerService.getFollowersListService(userId, order));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable int userId, @RequestParam(required = false) String order){
        return ResponseEntity.ok(userService.getFollowedList(userId, order));
    }
}
