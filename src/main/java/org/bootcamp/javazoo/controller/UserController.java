package org.bootcamp.javazoo.controller;

import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedList(@PathVariable int userId){
        List<SellerDto> followers = userService.getFollowedList(userId);
        return ResponseEntity.ok(followers);
    }
}
