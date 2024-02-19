package org.bootcamp.javazoo.controller;

import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;



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
    // Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersDto> getFollowersCount(@PathVariable Integer userId){
        Integer followersCount = userService.getFollowedList(userId, null).getFollowers().size();
        String userName = userService.getFollowedList(userId, null).getUser_name();
        CountFollowersDto countFollowersDto = new CountFollowersDto(userId, userName, followersCount);
        return ResponseEntity.ok(countFollowersDto);
    }
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable int userId, @RequestParam(required = false) String order){
        return ResponseEntity.ok(userService.getFollowedList(userId, order));
    }
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> addFollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return ResponseEntity.ok(sellerService.addFollow(userId, userIdToFollow));
    }
}
