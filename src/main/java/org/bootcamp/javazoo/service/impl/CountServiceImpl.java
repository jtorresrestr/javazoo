package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.impl.SellerRepositoryImpl;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.bootcamp.javazoo.service.interfaces.ICountService;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountServiceImpl implements ICountService {
    private UserServiceImpl userService;
    private Integer userId;

    public CountServiceImpl(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    private final ISellerRepository sellerRepository;


    @Override
    public CountFollowersDto getFollowersListCount(Integer userId) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("Seller not found");
        }
        List<UserDto> followers = seller.getFollowers().stream()
                .map(UserDto::convertUserToUserDto)
                .toList();

        Integer followersCount = followers.size();

        if(followersCount == 0){
            return new CountFollowersDto(userId, seller.getName(), 0);
        }
        return new CountFollowersDto(userId, seller.getName(), followersCount);
    }
}
