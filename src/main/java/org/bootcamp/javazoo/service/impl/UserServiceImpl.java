package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;
    ISellerService sellerService;


    public UserServiceImpl(IUserRepository userRepository, ISellerService sellerService) {
        this.userRepository = userRepository;
        this.sellerService = sellerService;
    }

    @Override
    public FollowersListDto getFollowedList(Integer userId, String order) {
        User user =  userRepository.getById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        List<UserDto> sellers;
        if (order == null) {
            sellers = user.getFollowed().stream()
                    .map(UserDto::convertUserToUserDto)
                    .toList();
        } else if (order.equals("name_asc")) {
            sellers = user.getFollowed().stream()
                    .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                    .map(UserDto::convertUserToUserDto)
                    .toList();
        } else {
            sellers = user.getFollowed().stream()
                    .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                    .map(UserDto::convertUserToUserDto)
                    .toList();
        }

        return new FollowersListDto(userId, user.getName(), sellers);
    }

    @Override
    public MessageDto unfollowSeller(Integer userId, Integer userIdToUnfollow) {

        User user = getUserById(userId);
        Seller seller = sellerService.getById(userIdToUnfollow);

        List<Seller> followedList = user.getFollowed();
        if(!followedList.removeIf(s -> s.equals(seller))){
            throw  new NotFoundException("User not follow the seller");
        }
        user.setFollowed(followedList);
        userRepository.unfollowSeller(user);

        List<User> followerList = seller.getFollowers();
        followerList.removeIf(u -> u.equals(user));
        seller.setFollowers(followerList);
        sellerService.removeFollower(seller);

        return new MessageDto("You stopped following the seller");
    }

    @Override
    public User getUserById(Integer userId){
        User user = userRepository.getById(userId);
        if(user == null) throw new NotFoundException("User not found");
        return user;
    }

    @Override
    public List<Seller> getUserFollowed(Integer userId){
        User user = getUserById(userId);
        List<Seller> followed = user.getFollowed();
        if(followed.isEmpty()) throw new NotFoundException("Este usuario con id: " + userId + " no sigue a ningun vendedor");
        /* return user.getFollowed().stream()
                .map(seller -> sellerService.getById(seller.getId()))
                .collect(Collectors.toList());

         */
        return followed;


    }
}
