package org.bootcamp.javazoo.controller;

import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final ISellerService sellerService;
    private final ISellerService followersCount;

    public UserController(IUserService userService, ISellerService sellerService, ISellerService followersCount) {
        this.userService = userService;
        this.sellerService = sellerService;
        this.followersCount = followersCount;
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersListDto> getFollowersList(@PathVariable Integer userId) {
        return ResponseEntity.ok(sellerService.getFollowersListService(userId));
    }
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable int userId){
        return ResponseEntity.ok(userService.getFollowedList(userId));
    }
    // Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersDto> getFollowersCount(@PathVariable Integer userId){
        return ResponseEntity.ok(followersCount.getFollowersListCount(userId));
    }

}
